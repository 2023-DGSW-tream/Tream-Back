package hs.kr.dgsw.treamprototype.global.client.social.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class KakaoAuthRequest {
    @JsonProperty("grant_type")
    private String grantType;
    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("redirect_uri")
    private String redirectUri;
    @JsonProperty("code")
    private String code;
    @JsonProperty("client_secret")
    private String clientSecret;

}
