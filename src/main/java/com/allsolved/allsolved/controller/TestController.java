package com.allsolved.allsolved.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "docker-jenkins-test12412";
    }

    @RequestMapping("/")
    public String mainMathod() {
        return "도커-젠킨스 성공";
    }

    //젠킨스 트리거 테스트 + Docker
//sads
}
