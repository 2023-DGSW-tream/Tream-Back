package hs.kr.dgsw.treamprototype.domain.auth.strategy.strategy;

import hs.kr.dgsw.treamprototype.domain.auth.strategy.SocialAuthStrategy;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.enums.SocialAuthStrategyName;
import hs.kr.dgsw.treamprototype.global.properties.SocialAuthProperties;
import hs.kr.dgsw.treamprototype.global.client.kakao.KaKaoAuthClient;
import hs.kr.dgsw.treamprototype.global.client.kakao.KakaoUserInfoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoAuthStrategy implements SocialAuthStrategy {

    private final KaKaoAuthClient kaKaoAuthClient;
    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final SocialAuthProperties socialAuthProperties;

    @Override
    public String execute(String code) {
        return kakaoUserInfoClient.getUserInfo(
                kaKaoAuthClient.getToken(
                        creatRequest(code)
                ).getAccessToken()
        ).getKakao_account().getEmail();
    }

    @Override
    public SocialAuthStrategyName getStrategyName() {
        return SocialAuthStrategyName.KAKAO;
    }

    private String creatRequest(String code) {
        return "code=" + code + '&' +
                "client_id=" + socialAuthProperties.getKakao().getClientId() + '&' +
                "client_secret=" + socialAuthProperties.getKakao().getClientSecret() + '&' +
                "redirect_uri=" + socialAuthProperties.getKakao().getRedirectUrl() + '&' +
                "grant_type=" + socialAuthProperties.getKakao().getGrantType();
    }
}
