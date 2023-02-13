package wkyyzl.cn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import wkyyzl.cn.bean.Person;
import wkyyzl.cn.util.RedisUtils;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTests {

    @Autowired
    Person person;

    @Resource
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Test
    void redisTest(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("hello", "world");
        String hello = operations.get("hello");
        System.out.println(hello);
    }

    @Test
    void redisTest2(){
        redisUtils.set("hello", "world");
        String hello = (String) redisUtils.get("hello");
        System.out.println(hello);
    }

    @Test
    void contextLoads() {
        redisUtils.set(person.getUserName(), person);
        Person getPerson = (Person) redisUtils.get(person.getUserName());
        System.out.println(getPerson);
    }

}
