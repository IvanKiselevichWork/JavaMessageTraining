package com.epam.publisher.web.controller;

import com.epam.publisher.model.Message;
import com.epam.publisher.service.publish.PublishServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "api/v1", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class PublishController {

    private PublishServiceImpl publishService;

    @PostMapping(value = "/publish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> publish(@RequestBody Message message) {
        log.info("Create message: {}", message);
        return ResponseEntity.of(Optional.of(publishService.publishMessageToTopic(message)));
    }
}
