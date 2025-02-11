package com.lucasgusmao.gestao_de_estoque_api.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private String token;

    public String getToken() {
        return token;
    }

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
