package xyz.alexhaoge.zhuanglang.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.pojo.Section;

public interface LessonDAO extends JpaRepository<Lesson, Integer> {
    List<Lesson> findBySection(Section section);
}