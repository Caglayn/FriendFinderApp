package com.bilgeadam.controller;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.manager.AuthServiceManager;
import com.bilgeadam.service.S3ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
@RequestMapping("")
public class RegisterController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthServiceManager authServiceManager;
    private final S3ManagerService s3ManagerService;

    public RegisterController(AuthServiceManager authServiceManager, S3ManagerService s3ManagerService) {
        this.authServiceManager = authServiceManager;
        this.s3ManagerService = s3ManagerService;
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping ("/register")
    public Object register(String firstname, String lastname, String email, String password, String country, MultipartFile image){
        ModelAndView model = new ModelAndView();

        String profileId = authServiceManager.register(RegisterRequestDto.builder()
                .password(password)
                .email(email)
                .lastname(lastname)
                .name(firstname)
                .country(country)
                .build()).getBody();

        File file = new File(profileId+".png");
        try {
            image.transferTo(file);
            s3ManagerService.putObject(profileId + ".png", image);
            model.setViewName("login");
        } catch (Exception e) {
            log.error("Resim yuklenemedi... : {}", e.getMessage());
            model.setViewName("register");
        }


        return model;
    }
}
