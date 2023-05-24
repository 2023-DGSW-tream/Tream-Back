package hs.kr.dgsw.treamprototype.domain.auth.strategy.strategy;

import hs.kr.dgsw.treamprototype.domain.auth.factory.SocialType;
import hs.kr.dgsw.treamprototype.domain.auth.factory.SocialAuthRequestFactory;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.SocialAuthStrategy;
import hs.kr.dgsw.treamprototype.domain.auth.strategy.enums.SocialAuthStrategyName;
import hs.kr.dgsw.treamprototype.global.client.google.GoogleAuthClient;
import hs.kr.dgsw.treamprototype.global.client.google.GoogleUserInfoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleAuthStrategy implements SocialAuthStrategy {
    private final GoogleAuthClient googleAuthClient;

    private final GoogleUserInfoClient googleUserInfoClient;

    public final SocialAuthRequestFactory socialAuthRequestFactory;

    @Override
    public String execute(String code) {
        return googleUserInfoClient.getUserInfo(
                googleAuthClient.getToken(
                      socialAuthRequestFactory.createRequest(
                              SocialType.GOOGLE, code
                      )
                ).getAccessToken()
        ).getEmail();
    }

    @Override
    public SocialAuthStrategyName getStrategyName() {
        return SocialAuthStrategyName.GOOGLE;
    }
}
