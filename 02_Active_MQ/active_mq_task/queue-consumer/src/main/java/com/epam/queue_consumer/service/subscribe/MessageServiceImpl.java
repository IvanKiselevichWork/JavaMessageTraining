package com.epam.queue_consumer.service.subscribe;

import com.epam.queue_consumer.model.Message;
import com.epam.queue_consumer.model.MessageWithReplyTo;
import com.epam.queue_consumer.model.Reply;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl {

    private static final String CONSUMER = "TEMP_CONSUMER";
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = "${app.queue-temp}")
    public void listenMessage1(@Payload MessageWithReplyTo message) {
        log.info("Get message: {}", message);
        Reply reply = new Reply(CONSUMER, new Message(message.getValue()));
        jmsTemplate.convertAndSend(message.getReplyTo(), reply);
        log.info("Sent reply: {}", reply);
    }
}
