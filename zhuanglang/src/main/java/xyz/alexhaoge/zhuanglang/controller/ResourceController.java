package xyz.alexhaoge.zhuanglang.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.utils.Result;
import xyz.alexhaoge.zhuanglang.pojo.Resource;
import xyz.alexhaoge.zhuanglang.service.ResourceService;

@RestController
public class ResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);

    @Value("${web.upload.path}")
    private String uploadPath;

    @Autowired
    ResourceService rService;

    @GetMapping("/api/library/lesson/{lesson}/resource")
    public List<Resource> listByBelong(@PathVariable("lesson") int lesson) {
        return rService.listByBelong(lesson);
    }

    @GetMapping("/api/library/resource/{resource}")
    public Resource listByID(@PathVariable("resource") int resouce) {
        return rService.get(resouce);
    }

    @GetMapping("/api/library/resources")
    public List<Resource> list() {
        return rService.list();
    }

    @CrossOrigin
    @PostMapping("/api/library/resource/delete")
    public Result delete(@RequestBody Resource resource) {
        int id = resource.getId();
        String pathName = rService.get(id).getPath();
        rService.deleteByID(id);
        Path path = Paths.get(uploadPath + pathName);
		try {
            boolean isDel = Files.deleteIfExists(path);
            if(isDel) {
                return new Result(200);
            } else {
                return new Result(400);
            }
        } catch (Exception e) {
           return new Result(400); 
        }
    }

    @CrossOrigin
    @PostMapping("/api/library/resource/new")
    public Resource add(@RequestBody Resource resource) {
        LOG.info("===========Resource new=========");
        rService.addOrUpd(resource);
        return resource;
    }

    @CrossOrigin
    @PostMapping("/api/library/resource/edit")
    public Result edit(@RequestBody Resource resource) {
        rService.updateNameAndNoteByID(resource.getName(), resource.getNote(), resource.getId());
        return new Result(200);
    }
}