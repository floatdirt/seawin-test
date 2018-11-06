/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.abstractImpl;

/**
 * 
 * @author lijin
 * @version $Id: AbstractA.java, v 0.1 2018年7月24日 上午9:16:30 lijin Exp $
 */
public abstract class AbstractA {
    
    private String aa;
    public void doBiz(){
        testA();
    }
    public abstract void   testA();

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

}
