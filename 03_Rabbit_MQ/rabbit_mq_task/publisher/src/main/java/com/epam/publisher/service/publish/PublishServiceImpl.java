package com.epam.publisher.service.publish;

import com.epam.publisher.model.MyMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublishServiceImpl {

    private final RabbitTemplate rabbitTemplate;

    public MyMessage publishMessageToTopic(MyMessage myMessage) {
        rabbitTemplate.convertAndSend("ex", "rk.1", myMessage);
        rabbitTemplate.convertAndSend("ex", "rk.2", myMessage);
        log.info("Message sent: {}", myMessage);
        return myMessage;
    }

}
