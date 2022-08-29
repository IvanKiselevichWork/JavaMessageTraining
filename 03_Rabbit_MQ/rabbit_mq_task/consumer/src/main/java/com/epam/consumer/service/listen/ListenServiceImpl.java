package com.epam.consumer.service.listen;

import com.epam.consumer.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenServiceImpl {

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "queue1"),
                    key = "rk.1",
                    exchange = @Exchange(name = "ex", type = ExchangeTypes.TOPIC)
            )
    )
    public void listen1(Message message) {
        log.info("Get message to consumer 1: {}", message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "queue2"),
                    key = "rk.2",
                    exchange = @Exchange(name = "ex", type = ExchangeTypes.TOPIC)
            )
    )
    public void listen2(Message message) {
        log.info("Get message to consumer 2: {}", message);
    }

}
