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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import xyz.alexhaoge.zhuanglang.service.BookService;
import xyz.alexhaoge.zhuanglang.utils.Result;
import xyz.alexhaoge.zhuanglang.pojo.Book;
import xyz.alexhaoge.zhuanglang.pojo.Discipline;

@RestController
@Api(tags = {"书籍接口"})
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bService;

    @ApiOperation(value = "返回所有学科的所有书籍", notes = "返回所有学科的所有书籍对象的List")
    @GetMapping("/api/library/book")
    public List<Book> list() {
        return bService.list();
    }

    @ApiOperation(value = "返回对应ID的书籍", notes = "返回对应ID的书籍对象")
    @ApiImplicitParam(name = "bookId", value = "书籍编号", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/api/library/book/{id}")
    public Book listByID(@PathVariable("id") int bookId) {
        return bService.get(bookId);
    }

    @ApiOperation(value = "返回对应学科的所有书籍", notes = "返回对应学科的所有书籍对象的List")
    @ApiImplicitParam(name = "discipline", value = "学科", required = true, dataType = "Discipline", paramType = "path")
    @GetMapping("/api/library/{discipline}/book")
    public List<Book> listByDiscipline(@PathVariable("discipline") Discipline discipline) {
        return bService.listByDiscipline(discipline);
    }

    @ApiOperation(value = "新建一本书")
    @ApiImplicitParam(name = "book", value = "书籍", required = true, dataType = "Book", paramType = "body")
    @CrossOrigin
    @PostMapping("/api/new/library/book")
    public Book add(@RequestBody Book book) {
        bService.modify(book);
        LOG.info("==========Book created========");
        return book;
    }

    @ApiOperation(value = "删除一本书")
    @ApiImplicitParam(name = "book", value = "需要删除书籍对象，书籍ID需要正确，其余属性可以为空", required = true, dataType = "Book", paramType = "body")
    @PostMapping("/api/delete/library/book")
    public void delete(@RequestBody Book book) {
        bService.deleteByID(book.getId());
    }

    @ApiOperation(value = "修改一本书的名字")
    @ApiImplicitParam(name = "book", value = "需要修改的书籍对象，需要包含正确的ID和修改的书名，其余属性可以为空", required = true, dataType = "Book", paramType = "body")
    @PostMapping("/api/edit/library/book")
    public Result edit(@RequestBody Book book) {
        bService.updateNameByID(book.getName(), book.getId());
        return new Result(200);
    }
}