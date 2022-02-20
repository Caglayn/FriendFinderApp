package com.bilgeadam.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RegisterRequestDto {

    @NotNull
    @Size(min = 2)
    String name;

    @NotNull
    @Size(min = 2)
    String lastname;

    @NotNull
    @Email
    String email;

    @NotNull
    @Size(min = 8, max = 32)
    String password;

    String country;
    String city;
    String sex;
    int day;
    int month;
    int year;
}
