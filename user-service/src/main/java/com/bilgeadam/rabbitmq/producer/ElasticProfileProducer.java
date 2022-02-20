package com.bilgeadam.rabbitmq.producer;

import static com.bilgeadam.constants.RabbitKeys.*;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElasticProfileProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessageProfileSave(ProfileNotification notification) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY_PROFILE,notification);
    }
}
