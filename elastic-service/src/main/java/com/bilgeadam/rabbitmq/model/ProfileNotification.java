package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProfileNotification implements Serializable {
    private static final long serialVersionUID = 2079060354808548746L;
    String profileId;
    String firstname;
    String lastname;
    String email;
    String city;
    String country;
}
