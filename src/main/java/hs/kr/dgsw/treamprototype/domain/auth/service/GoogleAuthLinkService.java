package hs.kr.dgsw.treamprototype.domain.auth.service;

import hs.kr.dgsw.treamprototype.global.properties.SocialAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAuthLinkService {

    private final SocialAuthProperties socialAuthProperties;
    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&scope=email";

    public String execute(){
        return socialAuthProperties.getGoogle().getBaseUrl() +String.format(QUERY_STRING, socialAuthProperties.getGoogle().getClientId(), socialAuthProperties.getGoogle().getRedirectUrl());

    }
}
