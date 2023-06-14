package hs.kr.dgsw.treamprototype.global.lib.jwt;

import hs.kr.dgsw.treamprototype.global.lib.jwt.enums.JwtType;
import hs.kr.dgsw.treamprototype.global.properties.JwtProperties;
import hs.kr.dgsw.treamprototype.domain.auth.domain.RefreshToken;
import hs.kr.dgsw.treamprototype.global.redis.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final RedisService redisService;

    private final JwtProperties jwtProperties;


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
                .signWith(jwtProperties.getSigningKey(secretKey), SignatureAlgorithm.HS256)
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
}
