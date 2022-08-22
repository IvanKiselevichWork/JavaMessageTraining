package com.epam.publisher.service.publish;

import com.epam.publisher.model.Message;
import com.epam.publisher.model.MessageWithReplyTo;
import com.epam.publisher.service.reply.ReplyListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TemporaryQueue;

@Service
@Slf4j
public class PublishServiceImpl {

    @Value("${app.topic}")
    private String topic;
    @Value("${app.virtual-topic}")
    private String virtualTopic;
    @Value("${app.queue-temp}")
    private String tempQueue;

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    @Qualifier("temp")
    private JmsTemplate jmsTemplateTemp;

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

    @SneakyThrows
    public Message publishMessageToQueue(Message message) {
        ConnectionFactory connectionFactory = jmsTemplateTemp.getConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession();
        TemporaryQueue temporaryQueue = session.createTemporaryQueue();
        MessageConsumer consumer = session.createConsumer(temporaryQueue);
        consumer.setMessageListener(new ReplyListener(connection, session));

        MessageWithReplyTo messageWithReplyTo = new MessageWithReplyTo();
        messageWithReplyTo.setValue(message.getValue());
        messageWithReplyTo.setReplyTo(temporaryQueue.getQueueName());

        jmsTemplateTemp.convertAndSend(tempQueue, messageWithReplyTo);
        log.info("Message sent to queue with temp queue: {}", tempQueue);

        return message;
    }
}
