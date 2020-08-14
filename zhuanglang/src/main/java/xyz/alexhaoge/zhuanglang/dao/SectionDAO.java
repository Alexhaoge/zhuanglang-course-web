package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Section;

public interface SectionDAO extends JpaRepository<Section, Integer>{
    List<Section> findByBook(Book book);
}