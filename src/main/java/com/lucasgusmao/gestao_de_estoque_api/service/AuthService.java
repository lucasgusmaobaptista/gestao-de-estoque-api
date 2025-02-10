package com.lucasgusmao.gestao_de_estoque_api.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String authenticate() {
        return "token";
    }
}
