package hs.kr.dgsw.treamprototype.global.client.social.google;

import hs.kr.dgsw.treamprototype.global.client.social.dto.response.UserInfoResponse;
import hs.kr.dgsw.treamprototype.global.client.social.google.dto.response.GoogleUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "googleUser", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleUserInfoClient {
    @GetMapping("?alt=json&access_token={TOKEN}")
    GoogleUserInfoResponse getUserInfo(@PathVariable("TOKEN") String accessToken);
}
