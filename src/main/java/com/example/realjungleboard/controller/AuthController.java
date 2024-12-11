package com.example.realjungleboard.controller;

import com.example.realjungleboard.dto.AuthDto;
import com.example.realjungleboard.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public String signUp(AuthDto authDto) {
        authService.signUp(authDto);

        return "register success";
    }
}
