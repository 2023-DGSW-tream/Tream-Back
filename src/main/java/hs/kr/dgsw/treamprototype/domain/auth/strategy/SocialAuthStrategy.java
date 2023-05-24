package hs.kr.dgsw.treamprototype.domain.auth.strategy;

import hs.kr.dgsw.treamprototype.domain.auth.strategy.enums.SocialAuthStrategyName;

public interface SocialAuthStrategy {
    String execute(String code);

    SocialAuthStrategyName getStrategyName();
}

