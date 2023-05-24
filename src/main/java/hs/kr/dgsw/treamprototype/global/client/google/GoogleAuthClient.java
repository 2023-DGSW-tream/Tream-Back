package hs.kr.dgsw.treamprototype.global.client.google;

import hs.kr.dgsw.treamprototype.domain.auth.strategy.dto.request.SocialAuthRequest;
import hs.kr.dgsw.treamprototype.global.client.google.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "googleAuth", url = "https://oauth2.googleapis.com")
public interface GoogleAuthClient {
    @PostMapping("/token")
    GoogleTokenResponse getToken(@RequestBody SocialAuthRequest request);
}
