package xyz.alexhaoge.zhuanglang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.pojo.Resource;
import xyz.alexhaoge.zhuanglang.service.ResourceService;

@RestController
public class ResourceController {
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
}