package com.bilgeadam.service;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.LoginDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.manager.AuthServiceManager;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthServiceManager authServiceManager;

    public LoginService(AuthServiceManager authServiceManager) {
        this.authServiceManager = authServiceManager;
    }

    public DoLoginResponseDto login(LoginDto dto) {
        return authServiceManager.doLogin(DoLoginRequestDto.builder().username(dto.getEmail()).password(dto.getPassword()).build()).getBody();
    }
}
