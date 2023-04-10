package wkyyzl.cn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.db.User;
import wkyyzl.cn.service.IDeviceService;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

@SpringBootTest
public class IDeviceServiceTest {

    @Autowired
    private IDeviceService iDeviceService;

    @Test
    void truncateTable() {
        iDeviceService.truncateTable();
    }

    @Test
    void disjointTest() {
        HashSet<User> users1 = new HashSet<>();
        HashSet<User> users2 = new HashSet<>();

        System.out.println(users1.equals(users2));

        for (int i = 0; i < 3; i++) {
            User user = new User().selectById(i);
            if (user != null) {
                users1.add(user);
            }
        }
        System.out.println(users1);

        for (int i = 0; i < 3; i++) {
            User user = new User().selectById(i);
            if (user != null) {
                //users1.add(user);
                users2.add(user);
            }
        }
        System.out.println(users1);
        System.out.println(users2);

        boolean disjoint = Collections.disjoint(users1, users2);

        System.out.println(disjoint);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(users1, 1);
        objectObjectHashMap.put(users2, 2);
        System.out.println(objectObjectHashMap);
        System.out.println(users1.hashCode());
        System.out.println(users2.hashCode());
        System.out.println(System.identityHashCode(users1));
        System.out.println(System.identityHashCode(users2));
    }

}
