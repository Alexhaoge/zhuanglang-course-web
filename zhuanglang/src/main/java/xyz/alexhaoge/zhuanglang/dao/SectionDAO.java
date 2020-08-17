package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Section;

public interface SectionDAO extends JpaRepository<Section, Integer>{
    @EntityGraph(value = "sectionGraph", type = EntityGraph.EntityGraphType.FETCH)
    List<Section> findByBelongOrderByNumber(Book belong);
}