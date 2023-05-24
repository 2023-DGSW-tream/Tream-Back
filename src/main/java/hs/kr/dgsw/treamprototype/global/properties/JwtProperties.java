package hs.kr.dgsw.treamprototype.global.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperties {
    private String accessKey;
    private String refreshKey;
    private Long accessExp;
    private Long refreshExp;
}
