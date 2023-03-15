package wkyyzl.cn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.service.IDeviceService;

@SpringBootTest
public class IDeviceServiceTest {

    @Autowired
    private IDeviceService iDeviceService;

    @Test
    void truncateTable() {
        iDeviceService.truncateTable();
    }

}
