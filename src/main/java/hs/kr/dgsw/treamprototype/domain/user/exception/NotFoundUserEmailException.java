package hs.kr.dgsw.treamprototype.domain.user.exception;

import hs.kr.dgsw.treamprototype.global.exception.TreamException;
import org.springframework.http.HttpStatus;

public class NotFoundUserEmailException extends TreamException {
    public static final NotFoundUserEmailException EXCEPTION  = new NotFoundUserEmailException();


    public NotFoundUserEmailException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다");
    }
}
