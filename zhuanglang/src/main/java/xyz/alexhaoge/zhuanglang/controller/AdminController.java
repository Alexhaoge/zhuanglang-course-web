package xyz.alexhaoge.zhuanglang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(tags = {"管理面板接口"}, hidden = true)
public class AdminController {
    
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public String foo() {
        LOG.info("not develop");
        return "bar";
    }
}