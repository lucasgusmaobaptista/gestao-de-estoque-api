package com.lucasgusmao.gestao_de_estoque_api.controller;

import com.lucasgusmao.gestao_de_estoque_api.dto.AuthDTO;
import com.lucasgusmao.gestao_de_estoque_api.dto.LoginResponseDTO;
import com.lucasgusmao.gestao_de_estoque_api.dto.RegisterDTO;
import com.lucasgusmao.gestao_de_estoque_api.model.user.User;
import com.lucasgusmao.gestao_de_estoque_api.repository.UserRepository;
import com.lucasgusmao.gestao_de_estoque_api.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO dto) {
        if(this.userRepository.findByUsername(dto.getUsername()) != null ) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getUsername(), encryptedPassword, dto.getRole());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
