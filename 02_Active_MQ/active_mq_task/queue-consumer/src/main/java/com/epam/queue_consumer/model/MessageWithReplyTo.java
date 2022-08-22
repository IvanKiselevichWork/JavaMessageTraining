package com.epam.queue_consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageWithReplyTo implements Serializable {

    private static final long serialVersionUID = 1;

    private String value;
    private String replyTo;
}
