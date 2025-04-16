package com.maintrot.basya.components;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generatetoken(String username, String phone, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("phone", phone);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();


    }

    public String getPhoneFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("phone", String.class);
    }

    public String getRoleFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Неверная JWT подпись");
        } catch (MalformedJwtException ex) {
            System.err.println("Неверный JWT токен");
        } catch (ExpiredJwtException ex) {
            System.err.println("JWT токен просрочен");
        } catch (UnsupportedJwtException ex) {
            System.err.println("JWT токен не поддерживается");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims строка пуста");
        }
        return false;
    }
}
