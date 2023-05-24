package hs.kr.dgsw.treamprototype.domain.auth.strategy;

import hs.kr.dgsw.treamprototype.domain.auth.strategy.enums.SocialAuthStrategyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@Service
public class SocialAuthStrategyFactory {
    private Map<SocialAuthStrategyName, SocialAuthStrategy> socialAuthStrategy;

    @Autowired
    public SocialAuthStrategyFactory(Set<SocialAuthStrategy> socialAuthStrategySet) {
        createStrategy(socialAuthStrategySet);
    }

    public SocialAuthStrategy findStrategy(SocialAuthStrategyName strategyName) {
        return socialAuthStrategy.get(strategyName);
    }
    private void createStrategy(Set<SocialAuthStrategy> strategySet) {
        socialAuthStrategy = new HashMap<SocialAuthStrategyName, SocialAuthStrategy>();
        strategySet.forEach(
                strategy ->socialAuthStrategy.put(strategy.getStrategyName(), strategy)
        );
     }
}

