package com.djsmdev.multitenant.service.impl;

import com.djsmdev.multitenant.dtos.LoginDTO;
import com.djsmdev.multitenant.dtos.RegisterDTO;
import com.djsmdev.multitenant.repositories.UserRepository;
import com.djsmdev.multitenant.security.JWTTokenProvider;
import com.djsmdev.multitenant.service.AuthService;
import com.djsmdev.multitenant.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public String login(LoginDTO login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JWTTokenProvider jwtTokenProvider = new JWTTokenProvider();


        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public void register(RegisterDTO register) {

    }
}
