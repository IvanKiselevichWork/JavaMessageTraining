package com.epam.virtual_consumer.service.subscribe;

import com.epam.virtual_consumer.model.Message;
import com.epam.virtual_consumer.model.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl {

    private static final String CONSUMER1 = "CONSUMER1";
    private static final String CONSUMER2 = "CONSUMER2";

    @Value("${app.queue}")
    private String queue;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "Consumer.1.VirtualTopic.virtual_topic")
    public void listenMessage1(@Payload Message message) {
        log.info("Get message: {}", message);
        Reply reply = new Reply(CONSUMER1, message);
        jmsTemplate.convertAndSend(queue, reply);
        log.info("Sent reply: {}", reply);
    }

    @JmsListener(destination = "Consumer.2.VirtualTopic.virtual_topic")
    public void listenMessage2(@Payload Message message) {
        log.info("Get message: {}", message);
        Reply reply = new Reply(CONSUMER2, message);
        jmsTemplate.convertAndSend(queue, reply);
        log.info("Sent reply: {}", reply);
    }
}
