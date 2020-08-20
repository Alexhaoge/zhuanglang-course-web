package xyz.alexhaoge.zhuanglang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import xyz.alexhaoge.zhuanglang.utils.Result;
import xyz.alexhaoge.zhuanglang.pojo.Teacher;
import xyz.alexhaoge.zhuanglang.service.TeacherService;

@Controller
public class LoginController {

    @Autowired
    TeacherService teacherService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody Teacher reqTeacher, HttpSession session) {
    // 对 html 标签进行转义，防止 XSS 攻击
        String username = reqTeacher.getUsername();
        username = HtmlUtils.htmlEscape(username);

        Teacher teacher = teacherService.get(username, reqTeacher.getPassword());
        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            return new Result(200);
        } else {
            return new Result(400);
        }
    }
}