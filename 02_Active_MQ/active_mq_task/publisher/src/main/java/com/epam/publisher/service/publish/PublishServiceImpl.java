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
public class PublishServiceImpl implements PublishService {

    @Value("${app.topic}")
    private String topic;

    private final JmsTemplate jmsTemplate;

    @Override
    public Message publishMessage(Message message) {
        jmsTemplate.convertAndSend(topic, message);
        log.info("Message sent: {}", message);
        return message;
    }
}
