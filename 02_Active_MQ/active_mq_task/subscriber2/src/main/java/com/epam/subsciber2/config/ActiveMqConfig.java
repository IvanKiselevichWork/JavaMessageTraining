package com.epam.subsciber2.config;

import com.epam.subsciber2.model.Message;
import com.epam.subsciber2.model.Reply;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
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

        converter.setTypeIdMappings(typeIdMappings);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("JMS_TYPE");

        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(false);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }
}
