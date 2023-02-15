package wkyyzl.cn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.User;

import java.util.List;

@SpringBootTest
public class ActiveRecordTests {

    @Test
    void getUsers(){
        User user = new User();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.gt("age", 24);
        queryWrapper.ge("age", 24);
        List<User> users = user.selectList(queryWrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

}
