package com.bilgeadam.rabbitmq.producer;

import static com.bilgeadam.constant.RabbitKeys.*;
import com.bilgeadam.rabbitmq.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(Notification notification){
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_CREATE_USER, notification);
        System.out.println("notification = " + notification.toString());
    }
}
