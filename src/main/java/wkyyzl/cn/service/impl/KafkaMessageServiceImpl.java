package wkyyzl.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import wkyyzl.cn.service.MessageService;

@Service
public class KafkaMessageServiceImpl implements MessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String id) {
        System.out.println("待发送的消息已纳入处理队列(kafka), id: " + id);
        kafkaTemplate.send("myTestTopic", id);
    }

    @Override
    public String doMessage() {
        return null;
    }
}
