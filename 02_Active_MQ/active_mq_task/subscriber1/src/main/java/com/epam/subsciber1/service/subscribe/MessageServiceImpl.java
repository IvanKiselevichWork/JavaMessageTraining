package com.epam.subsciber1.service.subscribe;

import com.epam.subsciber1.model.Message;
import com.epam.subsciber1.model.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl {

    private static final String SUBSCRIBER = "Subscriber1";

    @Value("${app.queue}")
    private String queue;

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${app.topic}")
    public void listenMessage(Message message) {
        log.info("Get message: {}", message);
        Reply reply = new Reply(SUBSCRIBER, message);
        jmsTemplate.convertAndSend(queue, reply);
    }
}
