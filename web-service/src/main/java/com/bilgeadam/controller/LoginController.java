package com.bilgeadam.controller;

import com.bilgeadam.dto.request.LoginDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.model.LoginPageModel;
import com.bilgeadam.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public Object login(@Valid LoginDto loginDto){
        DoLoginResponseDto response = loginService.login(loginDto);
        if (response.getError() == 200){
            return "redirect:/";
        }
        else{
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login");
            if (response.getError() == 410)
                modelAndView.addObject("loginerror", "Kullanici adı veye şifre hatalı");
            else if (response.getError() == 500){
                modelAndView.addObject("loginerror", "Beklenmeyen bir hata oluştu");
            }
            return modelAndView;
        }
    }
}
