package xyz.alexhaoge.zhuanglang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import xyz.alexhaoge.zhuanglang.utils.Result;
import xyz.alexhaoge.zhuanglang.utils.ResultFactory;
import xyz.alexhaoge.zhuanglang.pojo.Teacher;
import xyz.alexhaoge.zhuanglang.service.TeacherService;

@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    TeacherService teacherService;

    @CrossOrigin
    @PostMapping(value = "api/login/{rem}")
    public Result login(
        @RequestBody Teacher reqTeacher,
        @PathVariable("rem") int rem) {
    // 对 html 标签进行转义，防止 XSS 攻击
        String username = reqTeacher.getUsername();
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        LOG.info("========Teacher login called!=========");
        UsernamePasswordToken uToken = new UsernamePasswordToken(username, reqTeacher.getPassword());
        uToken.setRememberMe(rem == 1);
        try {
            subject.login(uToken);
            LOG.info("Teacher {} login success!", username);
            return ResultFactory.buildSuccessResult(username);
        } catch (AuthenticationException e) {
            return ResultFactory.buildFailResult("账号密码错误");
        }
    }

    @CrossOrigin
    @PostMapping("api/register")
    public Result register(@RequestBody Teacher teacher) {
        LOG.info("========Teacher registeration called!=========");
        String username = teacher.getUsername();
        String password = teacher.getPassword();
        username = HtmlUtils.htmlEscape(username);
        teacher.setUsername(username);
        if(teacherService.isExist(username)) {
            return ResultFactory.buildFailResult("用户名已存在");
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPwd = new SimpleHash("md5", password, salt, times).toString();
        teacher.setSalt(salt);
        teacher.setPassword(encodedPwd);
        LOG.info("Username: {}", teacher.getUsername());
        LOG.info("name: {}", teacher.getName());
        LOG.info("NKUorZhuanglang: {}", teacher.getNkuorzl());
        teacherService.add(teacher);
        LOG.info("========Teacher registeration successed!=========");
        return new Result(200, "注册成功");
    }

    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildResult(200, "登出成功", "登出成功");
    }

    @GetMapping(value = "api/authentication")
    public String authentication() {
        return "身份验证成功";
    }
}