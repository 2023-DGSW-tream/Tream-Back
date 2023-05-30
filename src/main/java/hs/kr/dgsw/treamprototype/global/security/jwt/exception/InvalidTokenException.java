package hs.kr.dgsw.treamprototype.global.security.jwt.exception;

import hs.kr.dgsw.treamprototype.global.exception.TreamException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends TreamException {
    public final static InvalidTokenException EXCEPTION = new InvalidTokenException();


    public InvalidTokenException() {
        super(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰");
    }
}
