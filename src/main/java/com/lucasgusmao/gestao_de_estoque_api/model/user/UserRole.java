package com.lucasgusmao.gestao_de_estoque_api.model.user;

public enum UserRole {
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
