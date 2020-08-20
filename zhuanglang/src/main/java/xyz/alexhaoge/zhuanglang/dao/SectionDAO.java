package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Section;

public interface SectionDAO extends JpaRepository<Section, Integer>{
    
    @EntityGraph(value = "sectionGraph", type = EntityGraph.EntityGraphType.FETCH)
    List<Section> findByBelongOrderByNumber(Book belong);

    @Modifying
    @Query("update Section s set s.number = ?1, s.note = ?2 where s.id = ?3")
    void updateNumberAndNoteByID(int number, String note, int id);
}