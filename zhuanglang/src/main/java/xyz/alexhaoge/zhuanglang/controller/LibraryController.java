package xyz.alexhaoge.zhuanglang.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xyz.alexhaoge.zhuanglang.utils.MD5;
import xyz.alexhaoge.zhuanglang.utils.Result;

@RestController
public class LibraryController {

    private static final Logger LOG = LoggerFactory.getLogger(LibraryController.class);

    @Value("${web.upload.path}")
    private String uploadPath;
    
    @CrossOrigin
    @PostMapping("api/upload/{sub}/{book}/{sec}/{les}")
    public String resourceUpload(
        @PathVariable("sub") String sub,
        @PathVariable("book") String book,
        @PathVariable("sec") String sec,
        @PathVariable("les") String les,
        MultipartFile file
    ){
        LOG.info("=============Resource upload controller called!============");
        String fa = this.uploadPath + sub + '/' + book + '/' + sec + '/' + les;
        LOG.info("father path: {}", fa);
        String md5;
        try (InputStream in = file.getInputStream()) {
           md5 = MD5.calcMD5(file.getInputStream());
        } catch (Exception e) {
            LOG.error("Get input stream or calculate MD5 failed!", e);
            LOG.info("==============Resource upload controller failed!===============");
            return "";
        }
        LOG.info("MD5: {}", md5);
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        File folder = new File(fa);
        filename = md5 + "." + suffix;
        File f = new File(folder, filename);
        if(!f.getParentFile().exists()) {
            f.getParentFile().mkdirs(); // 注意这里要用多层目录mkdirs
            LOG.info("=======father folder created=========");
        }
        LOG.info("filename: {}", filename);
        try {
            file.transferTo(f);
            LOG.info("=============Resource upload controller return 200!============");
            return sub + '/' + book + '/' + sec + '/' + les + "/" + filename;
        } catch (Exception e) {
           LOG.error("file copy problem!", e);
           LOG.info("==============Resource upload controller failed!===============");
           return "";
        }
    }

    @CrossOrigin
    @PostMapping("api/uploaddelete")
    @ResponseBody
    public Result deleteUpload(@RequestBody String pathName) {
        LOG.info("=========Resource delete before commit called!======");
        String pathName2 = pathName.replace("%2F", "/").replace("=", "");
        Path path = Paths.get(this.uploadPath + pathName2);
        LOG.info("Pathname: {}", pathName2);
		try {
            boolean isDel = Files.deleteIfExists(path);
            if(isDel) {
                return new Result(200, "File deleted");
            } else {
                return new Result(404, "File not exist");
            }
        } catch (Exception e) {
           return new Result(400, "Fail to delete!"); 
        }
    }
}