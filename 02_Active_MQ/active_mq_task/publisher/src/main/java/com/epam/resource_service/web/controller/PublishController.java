package com.epam.resource_service.web.controller;

import com.epam.resource_service.model.Message;
import com.epam.resource_service.service.publish.PublishService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/publish", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PublishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishController.class);

    private PublishService publishService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        LOGGER.info("Create message: {}", message);
        return ResponseEntity.of(Optional.of(publishService.publishMessage(message)));
    }
}
