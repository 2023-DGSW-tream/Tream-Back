package hs.kr.dgsw.treamprototype.global.security.jwt;

import hs.kr.dgsw.treamprototype.global.properties.JwtProperties;
import hs.kr.dgsw.treamprototype.domain.auth.domain.RefreshToken;
import hs.kr.dgsw.treamprototype.global.redis.RedisService;
import hs.kr.dgsw.treamprototype.global.security.jwt.exception.ExpiredTokenException;
import hs.kr.dgsw.treamprototype.global.security.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final RedisService redisService;

    private final JwtProperties jwtProperties;


    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String email, JwtType type) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        Long exp = 0L;
        String secretKey = "";

        switch (type) {
            case ACCESS:
                claims.put("type", type);
                secretKey = jwtProperties.getAccessKey();
                exp = jwtProperties.getAccessExp();
                break;
            case REFRESH:
                claims.put("type", type);
                secretKey = jwtProperties.getRefreshKey();
                exp = jwtProperties.getRefreshExp();
                break;
        }

        Date now = new Date();
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");

        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(getSigningKey(secretKey), SignatureAlgorithm.HS256)
                .compact();

        if (type.equals(JwtType.REFRESH)) {
            redisService.save(
                    RefreshToken.builder()
                            .refreshToken(token)
                            .email(email)
                            .build()
            );
        }

        return token;
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey(jwtProperties.getAccessKey()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredTokenException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }

    }
}
