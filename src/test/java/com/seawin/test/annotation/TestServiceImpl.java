/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.annotation;

/**
 * 
 * @author lijin
 * @version $Id: TestServiceImpl.java, v 0.1 2018年10月23日 下午6:24:10 lijin Exp $
 */
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    @CacheRedis(key = "test", expireTime = 10)
    public int test(int i) {
        return 0;
    }

    @Override
    @CacheRedis(key = "test1")
    public String test1(String i1) {
        return i1;
    }
}
