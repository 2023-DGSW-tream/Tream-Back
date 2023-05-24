package hs.kr.dgsw.treamprototype.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("auth")
public class SocialAuthProperties {

    private OAuth google;

    private OAuth kakao;

    @Setter
    @Getter
    public static class OAuth {
        private String clientId;
        private String clientSecret;
        private String redirectUrl;
        private String baseUrl;
        private String grantType;
    }

}
