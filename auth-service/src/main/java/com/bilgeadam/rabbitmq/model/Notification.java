package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification implements Serializable {
    private String message;
    private String sender;
    private String receiver;
    private long createAt;
    private String notificationId;


}
