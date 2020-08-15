package xyz.alexhaoge.zhuanglang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public void modify(Resource resource) {
        rDAO.save(resource);
    }

    public void deleteByID(int id) {
        rDAO.deleteById(id);
    }

    public List<Resource> listByBelong(int lessonId) {
        Lesson lesson = lService.get(lessonId);
        return rDAO.findAllByBelong(lesson);
    }
}