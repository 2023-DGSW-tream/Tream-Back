package hs.kr.dgsw.treamprototype.global.interceptor;

import hs.kr.dgsw.treamprototype.domain.user.domain.User;
import hs.kr.dgsw.treamprototype.global.annotation.AuthGuard;
import hs.kr.dgsw.treamprototype.global.lib.jwt.JwtProvider;
import hs.kr.dgsw.treamprototype.global.lib.jwt.JwtValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    private final JwtValidation jwtValidation;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthGuard authGuard = handlerMethod.getMethodAnnotation(AuthGuard.class);

        if (authGuard == null) {
            return true;
        }

        String token = extract(request);

        if (token == null) {
            return true;
        }

        User user = jwtValidation.validateToken(token);

        request.setAttribute("user", user.getId());

        return true;
    }

    private String extract(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ","");
        }
        return null;
    }
}
