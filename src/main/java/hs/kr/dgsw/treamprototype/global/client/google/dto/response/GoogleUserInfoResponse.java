package hs.kr.dgsw.treamprototype.global.client.google.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleUserInfoResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("verifiedEmail")
    private String verified_email;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("hd")
    private String hd;
}
