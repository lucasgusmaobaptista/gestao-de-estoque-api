package com.lucasgusmao.gestao_de_estoque_api.repository;

import com.lucasgusmao.gestao_de_estoque_api.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}