package hs.kr.dgsw.treamprototype.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("oauth2.google")
@Getter
@Setter
public class GoogleAuthInfo {
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String baseUrl;
    private String grantType;
}
