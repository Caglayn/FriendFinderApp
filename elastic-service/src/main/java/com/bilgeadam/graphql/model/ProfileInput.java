package com.bilgeadam.graphql.model;

import lombok.Data;

@Data
public class ProfileInput {
    private String profileid;
    private String firstname;
    private String lastname;
    private String email;
    private String city;
    private String country;
}
