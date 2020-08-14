package xyz.alexhaoge.zhuanglang.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.alexhaoge.zhuanglang.pojo.Teacher;

public interface TeacherDAO extends JpaRepository<Teacher, String>{
    Teacher findByUsername(String username);

    Teacher getByUsernameAndPassword(String username, String password);
}