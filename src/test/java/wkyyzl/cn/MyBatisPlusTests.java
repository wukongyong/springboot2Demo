package wkyyzl.cn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.Device;
import wkyyzl.cn.bean.User;
import wkyyzl.cn.mapper.UserMapper;
import wkyyzl.cn.service.impl.DeviceServiceImpl;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MyBatisPlusTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DeviceServiceImpl deviceService;

    @Test
    void getAllUsers(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void insertUser(){
        User user = new User();
        user.setUserName("wky");
        user.setAddress("address");
        user.setAge(24);
        user.setName("一二");
        user.setPassword("fdd");
        user.setMail("fjiwo&qq.com");

        userMapper.insert(user);
    }

    // 测试分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1,2); //查询第一页，查询1条数据

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        //wrapper.like("email", "qq.com");

        IPage<User> iPage = this.userMapper.selectPage(page, null);
        System.out.println("数据总条数： " + iPage.getTotal());
        System.out.println("数据总页数： " + iPage.getPages());
        System.out.println("当前页数： " + iPage.getCurrent());

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    @Test
    void saveBatchTest(){
        List<Device> list = new ArrayList<>();
        for(int i = 0 ;i < 100;i++){
            Device device = new Device();
            device.setSn("deviceTest" + i);
            list.add(device);
        }
        deviceService.saveBatch(list);
    }

}
