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
public class LoginDto {
    @Email
    @NotNull
    String email;
    @NotNull
    @Size(min = 8, max = 32)
    String password;
}
