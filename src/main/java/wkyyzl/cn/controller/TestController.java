package wkyyzl.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String handle01(){
        return "hello Spring Boot 2 !";
    }

}
