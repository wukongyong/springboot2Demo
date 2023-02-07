package wkyyzl.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkyyzl.cn.bean.Person;

@RestController
public class TestController {

    @Autowired
    Person person;

    @RequestMapping("/hello")
    public String handle01(){
        return "hello Spring Boot 2 !";
    }

    @RequestMapping("/person")
    public Person handle02(){
        return person;
    }



}
