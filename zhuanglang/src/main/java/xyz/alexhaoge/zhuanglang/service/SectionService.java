package xyz.alexhaoge.zhuanglang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Section;
import xyz.alexhaoge.zhuanglang.dao.SectionDAO;

@Service
public class SectionService {
    @Autowired
    SectionDAO sDAO;
    @Autowired
    BookService bService;

    public Section get(int id) {
        return sDAO.findById(id).orElse(null);
    }

    public List<Section> list(){
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return sDAO.findAll(sort);
    }

    public void modify(Section section) {
        sDAO.save(section);
    }

    public void deleteByID(int id) {
        sDAO.deleteById(id);
    }

    public List<Section> listByBelong(int bookId) {
        Book book = bService.get(bookId);
        return sDAO.findAllByBelong(book);
    }

}