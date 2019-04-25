package com.hisun.ibscore.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IbsRequestBody {
    boolean required() default true;
}
