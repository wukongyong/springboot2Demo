package wkyyzl.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wkyyzl.cn.bean.Person;
import wkyyzl.cn.bean.Result;

@RestController
public class LoginController {
    
    @Autowired
    Person person;

    @PostMapping("/login")
    public Result handleLogin() {
        return new Result(true, person, null);
    }

}
