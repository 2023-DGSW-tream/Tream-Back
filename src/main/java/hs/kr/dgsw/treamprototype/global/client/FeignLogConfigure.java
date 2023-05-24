package hs.kr.dgsw.treamprototype.global.client;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignLogConfigure {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Content-Type", "application/x-www-form-urlencoded");
    }
}
