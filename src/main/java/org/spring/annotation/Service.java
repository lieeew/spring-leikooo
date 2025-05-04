package org.spring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @date 2025/4/17
 * @description
 */
@Target(java.lang.annotation.ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Service {
    String value() default "singleton";
}
