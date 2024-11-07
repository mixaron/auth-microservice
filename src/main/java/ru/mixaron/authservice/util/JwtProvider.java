package ru.mixaron.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mixaron.authservice.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private long expirationMs; // Время жизни токена в миллисекундах

    public String generateToken(User user) {
        Instant now = Instant.now(); // Текущее время
        return Jwts.builder()
                    .setSubject(user.getMail())  // Устанавливаем e-mail как subject
                    .claim("role", user.getRole())  // Добавляем роль как claim
                    .claim("id", String.valueOf(user.getId()))
                    .setIssuedAt(Date.from(now)) // Время создания токена
                    .setExpiration(Date.from(now.plusMillis(expirationMs))) // Время истечения
                    .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256) // Подпись токена
                    .compact();
    }
}
