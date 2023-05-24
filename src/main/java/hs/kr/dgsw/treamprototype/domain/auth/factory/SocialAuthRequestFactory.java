package hs.kr.dgsw.treamprototype.domain.auth.factory;

import hs.kr.dgsw.treamprototype.global.properties.SocialAuthProperties;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.dto.request.SocialAuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SocialAuthRequestFactory {

    private final SocialAuthProperties socialAuthProperties;

    public SocialAuthRequest createRequest(SocialType socialType, String code) {
        switch (socialType) {
            case GOOGLE:
                return SocialAuthRequest.builder()
                        .code(code)
                        .clientId(socialAuthProperties.getGoogle().getClientId())
                        .clientSecret(socialAuthProperties.getGoogle().getClientSecret())
                        .redirectUri(socialAuthProperties.getGoogle().getRedirectUrl())
                        .grantType(socialAuthProperties.getGoogle().getGrantType())
                        .build();
            case KAKAO:
                return SocialAuthRequest.builder()
                        .code(code)
                        .clientId(socialAuthProperties.getKakao().getClientId())
                        .clientSecret(socialAuthProperties.getKakao().getClientSecret())
                        .redirectUri(socialAuthProperties.getKakao().getRedirectUrl())
                        .grantType(socialAuthProperties.getKakao().getGrantType())
                        .build();
            default:
                throw new RuntimeException("올바르지 않은 SocialType");
        }

    }
}