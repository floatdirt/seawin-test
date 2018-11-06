/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.handler.statics;


/**
 * 
 * @author lijin
 * @version $Id: PersonProxy.java, v 0.1 2018年10月23日 下午3:59:54 lijin Exp $
 * 静态代理产生于代码编译阶段，即一旦代码运行就不可变了
 * 网址https://juejin.im/post/591d8c8ba22b9d00585007dd
 */

public class PersonProxy {
    private IPerson iPerson;

    public PersonProxy(IPerson iPerson) {
        this.iPerson = iPerson;
    }

    public void doSomething() {
        System.out.println("Before Proxy");
        iPerson.doSomething();
        System.out.println("After Proxy");
    }

    public static void main(String[] args) {
        PersonProxy personProxy = new PersonProxy(new Person());
        personProxy.doSomething();
    }
}
