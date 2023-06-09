package wkyyzl.cn.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = "myTestTopic")
    public void onMyTestMessage(ConsumerRecord<String, String> record) {
        System.out.println("已完成短信发送业务(kafka), id: " + record.value());
    }

    @KafkaListener(topics = "gjzx_alarm")
    public void onMyTestMessage2(ConsumerRecord<String, String> record) {
        System.out.println("gjzx_alarm(kafka), id: " + record.value());
    }

}
