package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

public interface BookDAO extends JpaRepository<Book, Integer>{
    List<Book> findByDiscipline(Discipline discipline);
}