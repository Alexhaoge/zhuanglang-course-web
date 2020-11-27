package xyz.alexhaoge.zhuanglang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = {"下载文件接口"})
public class DownloadController {
    @Value("${web.upload.path}")
    private String uploadPath;

    @GetMapping("/api/downloadpath")
    public String getDownloadPath(){
        return this.uploadPath;
    }
}