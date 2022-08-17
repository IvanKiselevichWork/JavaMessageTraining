package com.epam.subsciber2.service.subscribe;

import com.epam.subsciber2.model.Message;
import com.epam.subsciber2.model.Reply;
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

    private static final String SUBSCRIBER = "Subscriber2";

    @Value("${app.queue}")
    private String queue;

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${app.topic}", containerFactory = "topicListenerFactory")
    public void listenMessage(@Payload Message message) {
        log.info("Get message: {}", message);
        Reply reply = new Reply(SUBSCRIBER, message);
        jmsTemplate.convertAndSend(queue, reply);
        log.info("Sent reply: {}", reply);
    }
}
