package xyz.alexhaoge.zhuanglang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.service.LessonService;
import xyz.alexhaoge.zhuanglang.utils.Result;

@RestController
public class LessonController {
    @Autowired
    LessonService lService;

    @GetMapping("/api/library/section/{section}/lesson")
    public List<Lesson> listByBelong(@PathVariable("section") int section) {
        return lService.listByBelong(section);
    }

    @GetMapping("/api/library/lesson/{lessonID}")
    public Lesson listByID(@PathVariable("lessonID") int lessonID) {
        return lService.get(lessonID);
    }

    @GetMapping("/api/library/lesson")
    public List<Lesson> list() {
        return lService.list();
    }

    @CrossOrigin
    @PostMapping("/api/library/lesson/new")
    public Lesson add(@RequestBody Lesson lesson) {
        lService.modify(lesson);
        return lesson;
    }

    @PostMapping("/api/library/lesson/delete")
    public void delete(@RequestBody Lesson lesson) {
        lService.deleteByID(lesson.getId());
    }

    @CrossOrigin
    @PostMapping("/api/library/lesson/edit")
    public Result edit(@RequestBody Lesson lesson) {
        lService.updateNumberAndNoteByID(lesson.getNumber(), lesson.getNote(), lesson.getId());
        return new Result(200);
    }
}