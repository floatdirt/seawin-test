/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.annotation;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheService {

    Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Pointcut(value = "@annotation(com.meizu.bro.service.test.CacheRedis)")
    public void pointCut() {
    }

    @Before(value = "pointCut() && @annotation(cacheRedis)")
    public void before(CacheRedis cacheRedis) {
        logger.info("the result of this method will be cached.");
    }

    @AfterReturning(value = "pointCut() && @annotation(cacheRedis)", returning = "result")
    public void after(CacheRedis cacheRedis, Object result) {
        String key = cacheRedis.key();
        int expireTime = cacheRedis.expireTime();
        //do something...
        logger.info("-----redis-----[key = " + key + "]" + "[expireTime = " + expireTime + "]");
        logger.info("the result of this method is" + result + ",and has been cached.");
    }

    //@Around("pointCut() && @annotation(cacheRedis)")
    //public Object setCache(ProceedingJoinPoint joinPoint,CacheRedis cacheRedis) {
    //    Object result = 1;
    //
    //    Method method = getMethod(joinPoint);//自定义注解类
    //    //CacheRedis cacheRedis = method.getAnnotation(CacheRedis.class);//获取key值
    //    String key = cacheRedis.key();
    //    int expireTime = cacheRedis.expireTime();
    //    //获取方法的返回类型,让缓存可以返回正确的类型
    //    Class returnType =((MethodSignature)joinPoint.getSignature()).getReturnType();
    //
    //    logger.info("[key = " + key + "]"+"[expireTime = " + expireTime + "]");
    //
    //    return result;
    //}
    //
    //private Method getMethod(ProceedingJoinPoint joinPoint) {
    //    //获取参数的类型
    //    Method method = null;
    //    try {
    //        Signature signature = joinPoint.getSignature();
    //        MethodSignature msig = null;
    //        if (!(signature instanceof MethodSignature)) {
    //            throw new IllegalArgumentException("该注解只能用于方法");
    //        }
    //        msig = (MethodSignature) signature;
    //        method = joinPoint.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
    //    } catch (NoSuchMethodException e) {
    //        logger.error("annotation no sucheMehtod", e);
    //    } catch (SecurityException e) {
    //        logger.error("annotation SecurityException", e);
    //    }
    //    return method;
    //}
}

