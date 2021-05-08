//package com.microstudy.users.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//@RequestMapping("users/ribbon")
//public class RibbonTestController {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping("/testRound")
//    public String getInfo(){
//        String test = restTemplate.getForObject("http://other-service/getInfo", String.class);
//        System.out.println(test);
//        return test;
//    }
//}
