/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.handler.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.seawin.test.handler.statics.IPerson;
import com.seawin.test.handler.statics.Person;

/**
 * 
 * @author lijin
 * @version $Id: PersonProxy.java, v 0.1 2018年10月23日 下午4:03:02 lijin Exp $
 * 动态代理的好处理时可以为我们生成任何一个接口的代理类，并将需要增强的方法织入到任意目标函数。但它仍然具有一个局限性，就是只有实现了接口的类，才能为其实现代理。
 * https://juejin.im/post/591d8c8ba22b9d00585007dd
    如果要被代理的对象是个实现类，那么Spring会使用JDK动态代理来完成操作（Spirng默认采用JDK动态代理实现机制）；
    如果要被代理的对象不是个实现类那么，Spring会强制使用CGLib来实现动态代理。
 */

public class PersonProxy implements InvocationHandler {
    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(delegate.getClass().getClassLoader(), delegate.getClass()
            .getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("Before Proxy");
            result = method.invoke(delegate, args);
            System.out.println("After Proxy");
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy();
        IPerson iperson = (IPerson) personProxy.bind(new Person());
        iperson.doSomething();
    }

}
