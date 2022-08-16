package com.epam.resource_service.service.publish;

import com.epam.resource_service.model.Message;

public interface PublishService {

    Message publishMessage(Message message);
}
