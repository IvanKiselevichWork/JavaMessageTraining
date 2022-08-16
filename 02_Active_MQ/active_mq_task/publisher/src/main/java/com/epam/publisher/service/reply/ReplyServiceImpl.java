package com.epam.publisher.service.reply;

import com.epam.publisher.model.Reply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReplyServiceImpl {

    @JmsListener(destination = "${app.queue}")
    public void listenReply(Reply reply) {
        log.info("Get reply: {}", reply);
    }
}
