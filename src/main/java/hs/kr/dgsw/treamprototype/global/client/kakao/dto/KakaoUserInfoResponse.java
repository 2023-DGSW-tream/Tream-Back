package hs.kr.dgsw.treamprototype.global.client.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class KakaoUserInfoResponse {
    private Long id;
    @Getter
    private KakaoAccount kakao_account;

    @Getter
    @AllArgsConstructor @NoArgsConstructor
    public static class KakaoAccount{
        private String email;
        private String nickname;
    }
}
