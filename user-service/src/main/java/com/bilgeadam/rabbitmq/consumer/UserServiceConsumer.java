package com.bilgeadam.rabbitmq.consumer;

import static com.bilgeadam.constants.RabbitKeys.*;
import com.bilgeadam.rabbitmq.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceConsumer {

    @RabbitListener(queues = QUE_CREATE_USER)
    public void consumeNotification(Notification notification){
        log.info("Bildirim geldi...: {}", notification.toString());
    }
}
