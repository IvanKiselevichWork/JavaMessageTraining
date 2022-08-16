package com.epam.publisher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply implements Serializable {

    private static final long serialVersionUID = 1;

    private String subscriber;
    private Message message;
}
