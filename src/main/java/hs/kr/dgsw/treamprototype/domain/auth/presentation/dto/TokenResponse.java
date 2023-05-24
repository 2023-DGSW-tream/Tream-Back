package hs.kr.dgsw.treamprototype.domain.auth.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor @NoArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}
