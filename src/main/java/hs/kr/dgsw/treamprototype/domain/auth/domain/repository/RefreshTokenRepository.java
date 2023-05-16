package hs.kr.dgsw.treamprototype.domain.auth.domain.repository;

import hs.kr.dgsw.treamprototype.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
