package hs.kr.dgsw.treamprototype.domain.auth.service;

import hs.kr.dgsw.treamprototype.global.properties.SocialAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoAuthLikeService {

    private final SocialAuthProperties socialAuthProperties;

    private static final String QUERY_STRING =
            "/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=kakao";

    public String execute() {
        return socialAuthProperties.getKakao().getBaseUrl() + String.format(
                        QUERY_STRING, socialAuthProperties.getKakao().getClientId(),
                        socialAuthProperties.getKakao().getRedirectUrl()
                );
    }

}
