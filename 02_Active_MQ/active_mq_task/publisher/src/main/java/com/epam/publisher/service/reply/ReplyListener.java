package com.epam.publisher.service.reply;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

@RequiredArgsConstructor
@Slf4j
public class ReplyListener implements MessageListener {

    private final Connection connection;
    private final Session session;

    @Override
    @SneakyThrows
    public void onMessage(Message message) {
        log.info("Temp reply received: {}", ((ActiveMQTextMessage) message).getText());
        session.close();
        connection.close();
    }
}
