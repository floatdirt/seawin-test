/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.handler.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.seawin.test.handler.statics.Person;

/**
 * 
 * @author lijin
 * @version $Id: PersonProxy.java, v 0.1 2018年10月23日 下午4:09:58 lijin Exp $
 * CGLIB是一个强大的高性能代码生成包（生成原理还没研究过），其在运行时期（非编译时期）生成被 代理对象的子类，并重写了被代理对象的所有方法，从而作为代理对象。
 * 当然CGLIB也具有局限性，对于无法生成子类的类（final类），肯定是没有办法生成代理子类的。
 * https://juejin.im/post/591d8c8ba22b9d00585007dd
 *  如果要被代理的对象是个实现类，那么Spring会使用JDK动态代理来完成操作（Spirng默认采用JDK动态代理实现机制）；
           如果要被代理的对象不是个实现类那么，Spring会强制使用CGLib来实现动态代理。
 */
public class PersonProxy implements MethodInterceptor {
    private Object delegate;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                                                                                throws Throwable {
        System.out.println("Before Proxy");
        //        Object result = proxy.invokeSuper(method, args);
        // 目标方法执行
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After Proxy");
        return result;
    }

    public static Person getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);

        enhancer.setCallback(new PersonProxy());
        return (Person) enhancer.create();
    }

    public static void main(String[] args) {
        PersonProxy proxy = new PersonProxy();
        Person person = proxy.getProxyInstance();
        person.doSomething();

    }
}
