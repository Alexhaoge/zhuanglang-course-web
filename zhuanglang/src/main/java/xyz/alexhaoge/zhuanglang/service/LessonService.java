package xyz.alexhaoge.zhuanglang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import xyz.alexhaoge.zhuanglang.pojo.Section;
import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.dao.LessonDAO;

@Service
public class LessonService {
    @Autowired
    LessonDAO lDAO;
    @Autowired
    SectionService sService;

    public Lesson get(int id) {
        return lDAO.findById(id).orElse(null);
    }

    public List<Lesson> list(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return lDAO.findAll(sort);
    }

    public void modify(Lesson lesson) {
        lDAO.save(lesson);
    }

    public void deleteByID(int id) {
        lDAO.deleteById(id);
    }

    public List<Lesson> listByBelong(int sectionId) {
        Section section = sService.get(sectionId);
        return lDAO.findAllByBelong(section);
    }
}