package com.djsmdev.multitenant.dtos;

import lombok.Builder;

@Builder
public record JwtAuthResponse(String accessToken, String tokenType) {
}
