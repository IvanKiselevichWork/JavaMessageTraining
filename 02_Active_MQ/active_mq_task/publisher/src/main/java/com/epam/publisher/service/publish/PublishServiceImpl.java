package com.epam.publisher.service.publish;

import com.epam.publisher.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PublishServiceImpl {

    @Value("${app.topic}")
    private String topic;
    @Value("${app.virtual-topic}")
    private String virtualTopic;

    private final JmsTemplate jmsTemplate;

    public Message publishMessageToTopic(Message message) {
        jmsTemplate.convertAndSend(topic, message);
        log.info("Message sent to topic: {}", message);
        return message;
    }

    public Message publishMessageToVirtualTopic(Message message) {
        jmsTemplate.convertAndSend(virtualTopic, message);
        log.info("Message sent to virtual topic: {}", message);
        return message;
    }
}
