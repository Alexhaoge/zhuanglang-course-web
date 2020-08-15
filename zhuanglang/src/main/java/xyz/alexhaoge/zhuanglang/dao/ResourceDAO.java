package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.pojo.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Integer> {
    List<Resource> findAllByBelong(Lesson belong);
}