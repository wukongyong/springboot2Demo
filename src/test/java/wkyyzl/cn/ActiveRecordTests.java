package wkyyzl.cn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.Device;
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

    @Test
    void insertDevice(){
        Device device = new Device();
        device.setSn("test1");
        device.setType(1);
        device.setHwv("jkljf");
        //device.insert();

        QueryWrapper<Device> queryWrapper = new QueryWrapper<Device>();
        queryWrapper.select("sn");
        List<Device> devices = device.selectList(queryWrapper);
        for (Device device1 : devices) {
            System.out.println(device1);
        }
    }

}
