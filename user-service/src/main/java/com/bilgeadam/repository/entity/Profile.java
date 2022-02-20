package com.bilgeadam.repository.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Profile implements Serializable {
    @Id
    private String id;
    private long authid;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private String country;
    private String city;
    private String gender;
    private String about;
    List<Interest> interests;
    Education education;
    Work work;

    @Document
    @Data
    public static class Education implements Serializable {
        String name;
        int from;
        int to;
        String about;
    }

    @Document
    @Data
    public static class Work implements Serializable{
        String company;
        String designation;
        int from;
        int to;
        String town;
        String description;
    }
}
