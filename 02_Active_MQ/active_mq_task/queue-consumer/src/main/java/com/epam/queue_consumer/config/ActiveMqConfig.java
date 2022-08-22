package com.epam.queue_consumer.config;

import com.epam.queue_consumer.model.MessageWithReplyTo;
import com.epam.queue_consumer.model.Reply;
import com.epam.queue_consumer.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@EnableJms
@Configuration
public class ActiveMqConfig {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("MESSAGE", Message.class);
        typeIdMappings.put("REPLY", Reply.class);
        typeIdMappings.put("MESSAGE_WITH_REPLY", MessageWithReplyTo.class);

        converter.setTypeIdMappings(typeIdMappings);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("JMS_TYPE");

        return converter;
    }
}
