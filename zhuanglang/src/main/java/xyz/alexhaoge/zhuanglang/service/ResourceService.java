package xyz.alexhaoge.zhuanglang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.alexhaoge.zhuanglang.pojo.Resource;
import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.dao.ResourceDAO;

@Service
public class ResourceService {
    @Autowired
    ResourceDAO rDAO;
    @Autowired
    LessonService lService;

    public Resource get(int id) {
        return rDAO.findById(id).orElse(null);
    }

    public List<Resource> list(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return rDAO.findAll(sort);
    }

    public void addOrUpd(Resource resource) {
        rDAO.save(resource);
    }

    @Transactional
    public void updateNameNoteAndBilibiliByID(String name, String note, String bilibili, int id) {
        rDAO.updateNameNoteAndBilibiliByID(name, note, bilibili, id);
    }

    public void deleteByID(int id) {
        rDAO.deleteById(id);
    }

    public List<Resource> listByBelong(int lessonId) {
        Lesson lesson = lService.get(lessonId);
        return rDAO.findAllByBelong(lesson);
    }
}