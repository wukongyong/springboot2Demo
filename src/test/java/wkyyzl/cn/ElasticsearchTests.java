package wkyyzl.cn;

import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.User;
import wkyyzl.cn.service.impl.DocumentServiceImpl;
import wkyyzl.cn.service.impl.IndexServiceImpl;

import java.io.IOException;

@SpringBootTest
public class ElasticsearchTests {

    @Autowired
    IndexServiceImpl indexService;

    @Autowired
    DocumentServiceImpl documentService;

    @Test
    void createIndex(){
        try {
            indexService.createIndex("user");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getIndex(){
        try {
            GetIndexResponse user = indexService.getIndexDetail("shopping");
            System.out.println(user.result().get("shopping"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void insertDoc(){

        User user = new User();
        user.setName("wky");
        user.setUserName("吴孔勇");
        user.setPassword("1111");
        user.setAge(28);
        user.setAddress("fqfwqf");
        user.setId(1234L);
        user.setMail("dhqjf@qq.com");

        //转换成json
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(user);
            documentService.createByJson("user", "1001", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
