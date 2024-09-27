package com.djsmdev.multitenant.controllers;

import com.djsmdev.multitenant.dtos.JwtAuthResponse;
import com.djsmdev.multitenant.dtos.LoginDTO;
import com.djsmdev.multitenant.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO login) {

        System.out.println(passwordEncoder.encode(login.password()));
        System.out.println(login.email());
        String token = authService.login(login);

        return new ResponseEntity<>(JwtAuthResponse.builder().accessToken(token).tokenType("Bearer").build(), HttpStatus.OK);

    }

}
