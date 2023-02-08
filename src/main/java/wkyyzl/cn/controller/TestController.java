package wkyyzl.cn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wkyyzl.cn.bean.Person;

import java.util.*;

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


    /**
     * 网页端cookie被禁的情况下可以用矩阵变量的方式传递session
     * 语法：cars/sell;low=34;brand=byd,audi,yd
     * SpringBoot默认是禁用了矩阵变量的功能
     *   手动开启：原理：对于路径的处理，UrlPathHelper进行解析
     *             removeSemicolonContent(移除分号内容) 支持矩阵变量
     * @param low
     * @param brand
     * @param path
     * @return
     */
    @RequestMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String, Object> map = new HashMap();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

}
