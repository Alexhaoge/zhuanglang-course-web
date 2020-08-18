package xyz.alexhaoge.zhuanglang.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.Result;
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

    @PostMapping("/api/delete/resource")
    public Result delete(@RequestBody Resource resource) {
        int id = resource.getId();
        String pathName = rService.get(id).getPath();
        rService.deleteByID(id);
        Path path = Paths.get(pathName);
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
}