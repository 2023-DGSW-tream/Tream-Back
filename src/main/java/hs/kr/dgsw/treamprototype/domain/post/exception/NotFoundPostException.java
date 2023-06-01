package hs.kr.dgsw.treamprototype.domain.post.exception;

import hs.kr.dgsw.treamprototype.global.exception.TreamException;
import org.springframework.http.HttpStatus;

public class NotFoundPostException extends TreamException {
    public static final NotFoundPostException EXCEPTION = new NotFoundPostException();


    public NotFoundPostException() {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 게시글");
    }
}
