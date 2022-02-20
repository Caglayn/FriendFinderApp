package com.bilgeadam.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoLoginResponseDto {
    String profileId;
    int status;

    /**
     * 200: success
     * 400: error
     * 500: Beklenmeyen hata
     */
    int error;
}
