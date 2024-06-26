package hs.kr.dgsw.treamprototype.domain.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_COMMON("COMMON"),
    ROLE_ADMIN("ADMIN");

    private final String role;
}
