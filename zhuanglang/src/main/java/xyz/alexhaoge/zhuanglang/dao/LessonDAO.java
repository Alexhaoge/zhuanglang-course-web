package xyz.alexhaoge.zhuanglang.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.pojo.Section;

public interface LessonDAO extends JpaRepository<Lesson, Integer> {
    
    @EntityGraph(value = "lessonGraph", type = EntityGraph.EntityGraphType.FETCH)
    List<Lesson> findByBelongOrderByNumber(Section belong);

    @Modifying
    @Query("update Lesson l set l.number = ?1, l.note = ?2 where l.id = ?3")
    void updateNumberAndNoteByID(int number, String note, int id);
}