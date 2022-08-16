package com.epam.publisher.config;

import com.epam.publisher.model.Message;
import com.epam.publisher.model.Reply;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.Map;

@EnableJms
@Configuration
public class ActiveMqConfig {

    @Value("${app.topic}")
    private String topic;

    @Value("${app.queue}")
    private String queue;

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topic);
    }

    @Bean
    public Queue messageQueue() {
        return new ActiveMQQueue(queue);
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("MESSAGE", Message.class);
        typeIdMappings.put("REPLY", Reply.class);

        converter.setTypeIdMappings(typeIdMappings);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("JMS_TYPE");

        return converter;
    }
}
