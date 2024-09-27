package com.djsmdev.multitenant.security;

import com.djsmdev.multitenant.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTTokenProvider {

    public String generateToken (Authentication authentication){

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + Long.parseLong(JwtUtils.JWT_EXPIRATION_DATE));

        Map<String, Object> claims = new HashMap<>();
        claims.put("tenant", userPrincipal.getTenant());
        claims.put("roles", roles);

        return Jwts.builder()
                .subject(userPrincipal.getEmail())
                .claims(claims)
                .issuedAt( new Date())
                .expiration(expireDate)
                .signWith(JwtUtils.key())
                .compact();
    }
}
