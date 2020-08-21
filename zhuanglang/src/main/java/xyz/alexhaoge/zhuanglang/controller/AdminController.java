package xyz.alexhaoge.zhuanglang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    public String foo() {
        LOG.info("not develop");
        return "bar";
    }
}