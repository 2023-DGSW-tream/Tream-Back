package hs.kr.dgsw.treamprototype.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .mvcMatchers("/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html");
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity)throws Exception {
        httpSecurity
                .formLogin().disable()
                .csrf().disable()
                .cors().disable()

                .authorizeRequests().antMatchers("/api/auth/**", "/api/post/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();
    }
}
