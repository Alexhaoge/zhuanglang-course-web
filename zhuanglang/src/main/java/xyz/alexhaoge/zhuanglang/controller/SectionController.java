package xyz.alexhaoge.zhuanglang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.pojo.Section;
import xyz.alexhaoge.zhuanglang.service.SectionService;

@RestController
public class SectionController {
    @Autowired
    SectionService sService;
    
    @GetMapping("/api/library/book/{book}/section")
    public List<Section> listByBelong(@PathVariable("book") int bookID) {
        return sService.listByBelong(bookID);
    }

    @GetMapping("/api/library/section/{sectionID}")
    public Section listByID(@PathVariable("sectionID") int sectionID) {
        return sService.get(sectionID);
    }

    @GetMapping("/api/library/section")
    public List<Section> list() {
        return sService.list();
    }

    @PostMapping("/api/delete/section")
    public void delete(@RequestBody Section section) {
        sService.deleteByID(section.getId());
    }
}