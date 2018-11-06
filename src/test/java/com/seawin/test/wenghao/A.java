/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.wenghao;

import java.util.List;

/**
 * 
 * @author lijin
 * @version $Id: A.java, v 0.1 2018年7月23日 下午2:57:52 lijin Exp $
 */
/**
 * public class A <T extends List<String>>  extends 可以删去，留着的话表示 需要A需要继承List<String>
 */
public class A<T extends List<String>> {
    T tt;

    public T getTt() {
        return tt;
    }

    public void setTt(T tt) {
        this.tt = tt;
    }

}
