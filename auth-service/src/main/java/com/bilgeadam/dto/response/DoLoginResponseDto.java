package com.bilgeadam.dto.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoLoginResponseDto {
    String profileId;
    String token;
    int status;

    /**
     * 200: success
     * 400: error
     * 411: token error
     * 500: Beklenmeyen hata
     */
    int error;
}
