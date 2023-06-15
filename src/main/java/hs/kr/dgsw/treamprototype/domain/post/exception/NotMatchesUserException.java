package hs.kr.dgsw.treamprototype.domain.post.exception;

import hs.kr.dgsw.treamprototype.global.exception.TreamException;
import org.springframework.http.HttpStatus;

public class NotMatchesUserException extends TreamException {
    public static final NotMatchesUserException EXCEPTION = new NotMatchesUserException();

    private NotMatchesUserException() {
        super(HttpStatus.BAD_REQUEST, "유저가 일치 하지 않음");
    }
}
