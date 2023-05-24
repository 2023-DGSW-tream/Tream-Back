package hs.kr.dgsw.treamprototype.domain.auth.presentation;

import hs.kr.dgsw.treamprototype.domain.auth.service.KakaoAuthLikeService;
import hs.kr.dgsw.treamprototype.domain.auth.service.SocialAuthService;
import hs.kr.dgsw.treamprototype.domain.auth.service.GoogleAuthLinkService;
import hs.kr.dgsw.treamprototype.domain.auth.presentation.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final GoogleAuthLinkService googleAuthLinkService;

    private final KakaoAuthLikeService kakaoAuthLikeService;

    private final SocialAuthService socialAuthService;

    @GetMapping("/google")
    public String getGoogleAuthLink() {
        return googleAuthLinkService.execute();
    }

    @GetMapping("/kakao")
    public String getKakaoAuthLink() {
        return kakaoAuthLikeService.execute();
    }

    @RequestMapping("/login/callback")
    public TokenResponse login(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state) {
        return socialAuthService.execute(code, state);
    }


}
