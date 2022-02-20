package com.bilgeadam.dto.request;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequestDto implements Serializable {
    private long authid;
    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private String country;
    private String city;
    private String gender;
    private String about;
}
