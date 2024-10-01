package com.ruoyi.common.annotation;


import cn.hutool.core.util.DesensitizedUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sensitives {

    DesensitizedUtil.DesensitizedType value();

    boolean isSensitive() default false;

    int subPrefix() default -1;

    String subPrefixValue() default "*";

    String subInfixValue() default "*";

    int subSuffix() default -1;

    String subSuffixValue() default "*";
}