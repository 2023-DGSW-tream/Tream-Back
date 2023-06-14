package hs.kr.dgsw.treamprototype.global.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthGuard {

    Role role() default Role.USER;

    enum Role {
        ANONYMOUS,
        USER,
        ADMIN
    }
}
