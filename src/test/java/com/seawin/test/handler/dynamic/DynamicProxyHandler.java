/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.handler.dynamic;

/**
 * 
 * @author lijin
 * @version $Id: DynamicProxyHandler.java, v 0.1 2018年7月24日 上午10:44:03 lijin Exp $
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        System.out.println("dynamic proxy handler constuctor: " + proxied.getClass());
        this.proxied = proxied;
    }

    /** 
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("dynamic proxy name: " + proxy.getClass());
        System.out.println("method: " + method.getName());
        System.out.println("args: " + Arrays.toString(args));
        
        Object invokeObject = method.invoke(proxied, args);
        if (invokeObject != null) {
            System.out.println("invoke object: " + invokeObject.getClass());
        } else {
            System.out.println("invoke object is null");
        }
        return invokeObject;
    }
    

  
}
