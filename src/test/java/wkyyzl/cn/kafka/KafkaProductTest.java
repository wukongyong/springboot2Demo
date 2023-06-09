package wkyyzl.cn.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.AlarmEvent;
import wkyyzl.cn.service.impl.KafkaMessageServiceImpl;

@SpringBootTest
public class KafkaProductTest {

    @Autowired
    private KafkaMessageServiceImpl kafkaMessageService;

    @Test
    void sendMessageToKafka() throws JsonProcessingException {
        //send普通字符串
        //kafkaMessageService.sendMessage("1213");

        //send json串
        AlarmEvent alarmEvent = new AlarmEvent();
        alarmEvent.setTopicName("gjzx_jz_alarm");
        alarmEvent.setTargetKey("test111");
        alarmEvent.setLevel("CLEARED");
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaMessageService.sendMessage(objectMapper.writeValueAsString(alarmEvent));

        //send protobuf串
    }

}
