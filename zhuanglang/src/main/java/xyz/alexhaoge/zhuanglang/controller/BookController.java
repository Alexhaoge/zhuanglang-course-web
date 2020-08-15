package xyz.alexhaoge.zhuanglang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.service.BookService;
import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/library/book")
    public List<Book> list() {
        return bookService.list();
    }

    @GetMapping("/api/library/book{id}")
    public Book listByID(@PathVariable("id") int bookId) {
        return bookService.get(bookId);
    }

    @GetMapping("/api/library/{discipline}/book")
    public List<Book> listByDiscipline(@PathVariable("discipline") Discipline discipline) {
        return bookService.listByDiscipline(discipline);
    }

}