package hs.kr.dgsw.treamprototype.global.client.social.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoAuthResponse {
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("idToken")
    private String id_token;
    @JsonProperty("expiresIn")
    private String expires_in;
    @JsonProperty("refreshToken")
    private String refresh_token;
    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;
    @JsonProperty("scope")
    private String scope;

    public String getAccessToken() {
        return "Bearer "+ accessToken;
    }
}
