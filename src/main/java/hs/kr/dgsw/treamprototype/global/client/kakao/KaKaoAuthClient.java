package hs.kr.dgsw.treamprototype.global.client.kakao;

import hs.kr.dgsw.treamprototype.global.client.FeignLogConfigure;
import hs.kr.dgsw.treamprototype.global.client.kakao.dto.KakaoAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "kakao-auth", url = "https://kauth.kakao.com", configuration = FeignLogConfigure.class)
public interface KaKaoAuthClient {

    @PostMapping("/oauth/token")
    KakaoAuthResponse getToken(@RequestBody String request);
}
