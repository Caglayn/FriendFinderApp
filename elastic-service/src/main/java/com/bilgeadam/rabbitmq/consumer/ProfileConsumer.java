package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.mapper.ProfileMapper;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import com.bilgeadam.repository.IProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileConsumer {
    private final IProfileRepository repository;
    private final ProfileMapper profileMapper;

    @RabbitListener(queues = "profile-save-queue")
    public void handleProfileSave(ProfileNotification notification){
        repository.save(profileMapper.toProfile(notification));
    }
}
