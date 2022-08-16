package com.epam.resource_service.service.publish;

import com.epam.resource_service.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService {

    @Value("${app.topic}")
    private String topic;

    private final JmsTemplate jmsTemplate;

    @Override
    public Message publishMessage(Message message) {
        jmsTemplate.convertAndSend(new ActiveMQTopic(topic), message);
        return message;
    }
}
