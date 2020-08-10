package xyz.alexhaoge.zhuanglang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.alexhaoge.zhuanglang.Teacher;
import xyz.alexhaoge.zhuanglang.dao.TeacherDAO;

@Service
public class TeacherService {
    @Autowired
    TeacherDAO teacherDAO;

    public Teacher getByName(String username) {
        return teacherDAO.findByUsername(username);
    }

    public boolean isExist(String username) {
        Teacher teacher = getByName(username);
        return teacher != null;
    }

    public Teacher get(String username, String password){
        return teacherDAO.getByUsernameAndPassword(username, password);
    }

    public void add(Teacher teacher) {
        teacherDAO.save(teacher);
    }

}