package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
