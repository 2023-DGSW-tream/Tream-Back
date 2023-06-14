package hs.kr.dgsw.treamprototype.global.lib.jwt;

import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.domain.user.domain.repository.UserRepository;
import hs.kr.dgsw.treamprototype.global.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtValidation {

    private final JwtProperties jwtProperties;

    private final UserRepository userRepository;

    public User validateToken(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(jwtProperties.getSigningKey(jwtProperties.getAccessKey()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return userRepository.findByEmail(claims.get("email", String.class))
                .orElseThrow(() -> new RuntimeException("d"));
    }
}
