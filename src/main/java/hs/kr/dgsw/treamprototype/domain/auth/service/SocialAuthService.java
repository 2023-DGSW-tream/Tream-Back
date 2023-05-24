package hs.kr.dgsw.treamprototype.domain.auth.service;

import hs.kr.dgsw.treamprototype.domain.auth.strategy.SocialAuthStrategy;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.enums.SocialAuthStrategyName;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.SocialAuthStrategyFactory;
import hs.kr.dgsw.treamprototype.domain.auth.presentation.dto.TokenResponse;
import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.domain.user.domain.repository.UserRepository;
import hs.kr.dgsw.treamprototype.global.security.jwt.JwtProvider;
import hs.kr.dgsw.treamprototype.global.security.jwt.JwtType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialAuthService {
    private final SocialAuthStrategyFactory socialAuthStrategyFactory;

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;
    @Transactional
    public TokenResponse execute(String code, String state) {

        System.out.println("state : " + state);

        SocialAuthStrategy socialAuthStrategy;

        if(state.equals("google")) {
            socialAuthStrategy = socialAuthStrategyFactory.findStrategy(SocialAuthStrategyName.GOOGLE);
        } else if(state.equals("kakao")) {
            socialAuthStrategy = socialAuthStrategyFactory.findStrategy(SocialAuthStrategyName.KAKAO);
        } else {
            throw new RuntimeException("올바르진 않은 요청 주소");
        }

        String email = socialAuthStrategy.execute(code);

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .build()
            );
        }

        return TokenResponse.builder()
                .accessToken(jwtProvider.createToken(email, JwtType.ACCESS))
                .refreshToken(jwtProvider.createToken(email, JwtType.REFRESH))
                .build();
    }
}