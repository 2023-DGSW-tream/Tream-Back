package hs.kr.dgsw.treamprototype.global.client.google.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class GoogleAuthRequest {
    String code;
    @JsonProperty("client_id")
    String clientId;
    @JsonProperty("client_secret")
    String clientSecret;
    @JsonProperty("redirect_uri")
    String redirectUri;
    @JsonProperty("grant_type")
    String grantType;
}
