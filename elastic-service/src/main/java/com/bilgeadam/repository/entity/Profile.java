package com.bilgeadam.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(indexName = "profile")
public class Profile {
    @Id
    private String id;
    private String profileId;
    private String firstname;
    private String lastname;
    private String email;
    private String country;
    private String city;
}
