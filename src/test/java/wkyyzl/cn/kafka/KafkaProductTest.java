package wkyyzl.cn.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.service.impl.KafkaMessageServiceImpl;

@SpringBootTest
public class KafkaProductTest {

    @Autowired
    private KafkaMessageServiceImpl kafkaMessageService;

    @Test
    void sendMessageToKafka() {
        kafkaMessageService.sendMessage("1213");
    }

}
