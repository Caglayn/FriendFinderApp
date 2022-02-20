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
public class DoLoginRequestDto {
    @Email(message = "Lutfen gecerli bir email adresi girin.")
    @NotNull(message = "Email adresi bos gecilemez")
    String username;

    @NotNull
    @Size(max = 32, min = 8, message = "Sifre alani en az 8 karakter en fazla 32 karakter olabilir.")
    String password;
}
