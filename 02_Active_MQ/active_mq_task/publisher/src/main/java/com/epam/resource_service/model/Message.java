package com.epam.resource_service.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private static final long serialVersionUID = 1;

    private String value;
}
