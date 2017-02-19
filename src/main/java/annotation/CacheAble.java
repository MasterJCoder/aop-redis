package annotation;

import java.lang.annotation.*;

/**
 * Created by lizhihui on 2017-02-19 13:37.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheAble {
    String key();

    Class type();

    int expireTime() default 3600;
}
