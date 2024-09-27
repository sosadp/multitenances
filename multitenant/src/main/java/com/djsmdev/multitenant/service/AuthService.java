package com.djsmdev.multitenant.service;

import com.djsmdev.multitenant.dtos.LoginDTO;
import com.djsmdev.multitenant.dtos.RegisterDTO;

public interface AuthService {

    String login (LoginDTO login);

    void register(RegisterDTO register);
}
