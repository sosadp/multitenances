package com.djsmdev.multitenant.dtos;

import lombok.Builder;

@Builder
public record LoginDTO(String email, String password) {
}
