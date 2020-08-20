package xyz.alexhaoge.zhuanglang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {
    @Value("${web.upload.path}")
    private String uploadPath;

    @GetMapping("/api/downloadpath")
    public String getDownloadPath(){
        return this.uploadPath;
    }
}