package hs.kr.dgsw.treamprototype.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TreamException.class)
    public ResponseEntity<ExceptionResponse> treamException(TreamException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ExceptionResponse(e.getMessage()));
    }
}
