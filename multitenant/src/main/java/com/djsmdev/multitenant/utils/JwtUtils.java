package com.djsmdev.multitenant.utils;

import com.djsmdev.multitenant.exceptions.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;

public final class JwtUtils {

    private static final String JWT_SECRET = "daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb" ;

    public static final String JWT_EXPIRATION_DATE = "3600000";

    public static String getUsername(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }

    public static String getUserRoles(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("roles")
                .toString();
    }

    public static String getTenant(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("tenant")
                .toString();
    }

    public static Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(JWT_SECRET));
    }

    public static boolean isTokenValid(String token){
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
        } catch (MalformedJwtException e){
            LoggerUtil.LOGGER.error("Invalid token {}", e.getMessage());
            throw new TokenException("Invalid token ",e.getCause());
        } catch (ExpiredJwtException e){
            LoggerUtil.LOGGER.error("JWT token is expired {}", e.getMessage());
            throw  new TokenException("JWT token is expired ",e.getCause());
        } catch (IllegalArgumentException e){
            LoggerUtil.LOGGER.error("JWT claims string is empty {}", e.getMessage());
            throw new TokenException("JWT claims string is empty ", e.getCause());
        }
        return false;
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.isNotBlank(bearerToken) && StringUtils.startsWith(bearerToken,"Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
