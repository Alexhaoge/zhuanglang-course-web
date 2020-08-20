package xyz.alexhaoge.zhuanglang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xyz.alexhaoge.zhuanglang.service.BookService;
import xyz.alexhaoge.zhuanglang.utils.Result;
import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

@RestController
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bService;

    @GetMapping("/api/library/book")
    public List<Book> list() {
        return bService.list();
    }

    @GetMapping("/api/library/book/{id}")
    public Book listByID(@PathVariable("id") int bookId) {
        return bService.get(bookId);
    }

    @GetMapping("/api/library/{discipline}/book")
    public List<Book> listByDiscipline(@PathVariable("discipline") Discipline discipline) {
        return bService.listByDiscipline(discipline);
    }

    @CrossOrigin
    @PostMapping("/api/library/book/new")
    public Book add(@RequestBody Book book) {
        bService.modify(book);
        LOG.info("==========Book created========");
        return book;
    }

    @PostMapping("/api/library/book/delete")
    public void delete(@RequestBody Book book) {
        bService.deleteByID(book.getId());
    }

    @PostMapping("/api/library/book/edit")
    public Result edit(@RequestBody Book book) {
        bService.updateNameByID(book.getName(), book.getId());
        return new Result(200);
    }
}