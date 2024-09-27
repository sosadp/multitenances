package com.djsmdev.multitenant.dtos;

import lombok.Builder;

@Builder
public record RegisterDTO( String name, String password, String email, String username, String role) {
}
