package xyz.alexhaoge.zhuanglang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xyz.alexhaoge.zhuanglang.dao.BookDAO;
import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

@Service
public class BookService {
    @Autowired
    BookDAO bookDAO;

    public Book get(int id) {
        return bookDAO.findById(id).orElse(null);
    }

    public List<Book> list(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return bookDAO.findAll(sort);
    }

    public void modify(Book book) {
        bookDAO.save(book);
    }

    public void deleteByID(int id) {
        bookDAO.deleteById(id);
    }

    public List<Book> listByDiscipline(Discipline discipline) {
        return bookDAO.findByDisciplineOrderByName(discipline);
    }

    @Transactional
    public void updateNameByID(String name, int id) {
        bookDAO.updateNameByID(name, id);
    }
}