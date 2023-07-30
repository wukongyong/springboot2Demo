package wkyyzl.cn.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wkyyzl.cn.bean.TargetAlarmRule;
import wkyyzl.cn.bean.TargetInfo;
import wkyyzl.cn.service.impl.KafkaMessageServiceImpl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Random;

@SpringBootTest
public class KafkaProductTest {

    @Autowired
    private KafkaMessageServiceImpl kafkaMessageService;

    @Test
    void sendMessageToKafka() throws JsonProcessingException, InterruptedException {
        //send普通字符串
        //kafkaMessageService.sendMessage("1213");

        Random random = new Random();

        TargetInfo target1 = new TargetInfo();
        target1.setId("target1");
        target1.setWindowSize("20");
        target1.setOperate(">");
        target1.setOperateNum("5");
        ArrayList<TargetAlarmRule> alarmRules1 = new ArrayList<>();
        TargetAlarmRule targetAlarmRule1 = new TargetAlarmRule();
        targetAlarmRule1.setRuleId("1");
        targetAlarmRule1.setName("targetAlarmRule1");
        alarmRules1.add(targetAlarmRule1);
        target1.setTargetAlarmRules(alarmRules1);

        TargetInfo target2 = new TargetInfo();
        target2.setId("target2");
        target2.setWindowSize("10");
        target2.setOperate("<");
        target2.setOperateNum("6");
        ArrayList<TargetAlarmRule> alarmRules2 = new ArrayList<>();
        TargetAlarmRule targetAlarmRule2 = new TargetAlarmRule();
        targetAlarmRule2.setRuleId("2");
        targetAlarmRule2.setName("targetAlarmRule2");
        alarmRules2.add(targetAlarmRule2);
        target2.setTargetAlarmRules(alarmRules2);

        ArrayList<TargetInfo> targetInfos = new ArrayList<>();
        targetInfos.add(target1);
        targetInfos.add(target2);

        //send json串
        while (true) {
            int num = random.nextInt(2);
            TargetInfo targetInfo = targetInfos.get(num);
            long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            int integer = random.nextInt(4);
            long time = milliSecond + integer * 1000L;
            targetInfo.setEventTime(Long.toString(time));
            targetInfo.setValue(String.valueOf(random.nextInt(10)));
            ObjectMapper objectMapper = new ObjectMapper();
            kafkaMessageService.sendMessage(objectMapper.writeValueAsString(targetInfo));

            Thread.sleep(1000L);
        }

        //send protobuf串
    }

}
