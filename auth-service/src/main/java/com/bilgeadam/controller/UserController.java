package com.bilgeadam.controller;

import static com.bilgeadam.constant.RestApiUrls.*;

import com.bilgeadam.dto.request.DoLoginRequestDto;
import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.repository.entity.User;
import com.bilgeadam.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(VERSION + AUTH)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(MESSAGE)
    @Operation(summary = "For try redis")
    public ResponseEntity<String> message(String mymessage){
        return ResponseEntity.ok(userService.merhaba(mymessage));
    }

    @PostMapping(DOLOGIN)
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto) {
        return ResponseEntity.ok(userService.getProfile(dto));
    }

    @PostMapping(VALIDATETOKEN)
    @Operation(summary = "Token validation")
    public ResponseEntity<Boolean> verifyToken(String token){
        return ResponseEntity.ok(userService.verifyToken(token));
    }

    @GetMapping(LOGOUT)
    @Operation(summary = "Logout")
    public ResponseEntity<Void> logout(){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        return ResponseEntity.ok().build();
    }

    @PostMapping(REGISTER)
    @Operation(summary = "Register new user")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequestDto dto) {
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping(FINDALL)
    @Operation(summary = "List all users from postgres")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

}
