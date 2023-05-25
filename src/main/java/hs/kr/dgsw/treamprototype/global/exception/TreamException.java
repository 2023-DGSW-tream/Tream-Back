package hs.kr.dgsw.treamprototype.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TreamException extends RuntimeException{
    private final HttpStatus httpStatus;

    private final String message;
}
