/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 自定义注解
 * @author lijin
 * @version $Id: CacheRedis.java, v 0.1 2018年10月23日 下午6:17:17 lijin Exp $
 * https://segmentfault.com/a/1190000013258647
 */
public @interface CacheRedis {
    String key();

    int expireTime() default 600;
}
