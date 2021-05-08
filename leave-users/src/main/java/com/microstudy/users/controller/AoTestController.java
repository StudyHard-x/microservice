package com.microstudy.users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@RequestMapping("users/testAo")
public class AoTestController {
    private static final Logger log = LoggerFactory.getLogger(AoTestController.class);

    @RequestMapping("/testRibbon")
    public Object testRibbon() {
        String targetUrl= "http://other-service/testAno";
        log.info("targetUrl={}",targetUrl);
        return null;
    }
}
