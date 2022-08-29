package com.epam.publisher.service.publish;

import com.epam.publisher.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublishServiceImpl {

    private final RabbitTemplate rabbitTemplate;

    public Message publishMessageToTopic(Message message) {
        rabbitTemplate.convertAndSend("rk.1", message);
        rabbitTemplate.convertAndSend("ex", "rk.1", message);
//        rabbitTemplate.convertAndSend("rk.2", message);
        log.info("Message sent: {}", message);
        return message;
    }

}
