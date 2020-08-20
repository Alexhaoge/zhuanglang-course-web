package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

public interface BookDAO extends JpaRepository<Book, Integer>{

    @EntityGraph(value = "bookGraph", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findByDisciplineOrderByName(Discipline discipline);

    @Modifying
    @Query("update Book b set b.name = ?1 where b.id = ?2")
    void updateNameByID(String name, int id);
}