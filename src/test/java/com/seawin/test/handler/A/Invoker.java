/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.handler.A;

import org.junit.Test;

/**
 * 
 * @author lijin
 * @version $Id: Invoker.java, v 0.1 2018年7月24日 上午10:20:29 lijin Exp $
 */
public class Invoker {

    private ProxyClass target;

    public ProxyClass getTarget() {
        return target;
    }

    public void setTarget(ProxyClass target) {
        this.target = target;
    }
    @Test
    public void test() {
        A a = new A();
        Invoker in = new Invoker();
        in.setTarget(a);
        in.getTarget().dobiz();
    }

}
