package hs.kr.dgsw.treamprototype.global.client.social.kakao;

import hs.kr.dgsw.treamprototype.global.client.social.dto.response.UserInfoResponse;
import hs.kr.dgsw.treamprototype.global.client.social.kakao.dto.KakaoUserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao-user-info", url = "https://kapi.kakao.com")
public interface KakaoUserInfoClient {
    @GetMapping("/v2/user/me")
    KakaoUserInfoResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
