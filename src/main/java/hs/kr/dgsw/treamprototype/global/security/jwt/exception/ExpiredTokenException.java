package hs.kr.dgsw.treamprototype.global.security.jwt.exception;

import hs.kr.dgsw.treamprototype.global.exception.TreamException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends TreamException {

    public final static ExpiredTokenException EXCEPTION = new ExpiredTokenException();


    public ExpiredTokenException() {
        super(HttpStatus.BAD_REQUEST, "만료된 토큰입니다");
    }
}
