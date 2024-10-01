package com.ruoyi.web.anno;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Range;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@ConstraintComposition(CompositionType.OR)
@Range(min = 3, max = 3, message = "状态异常")
@Range(min = 6, max = 6, message = "状态异常")
public @interface CodeRange {

    String message() default "状态异常";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
