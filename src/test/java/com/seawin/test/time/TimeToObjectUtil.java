/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.time;

import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author lijin
 * @version $Id: TimeToObjectUtil.java, v 0.1 2018年7月23日 下午4:10:07 lijin Exp $
 */
public class TimeToObjectUtil {

    
    @Test
    public void testTime(){
        B bb = new B();
        bb.setCreateTime("2018-7-23");
        String b = JSON.toJSONString(bb);
        JSON.DEFFAULT_DATE_FORMAT="yyyy-MM-dd";
        A aa = JSON.parseObject(b, A.class);
        System.out.println(aa.getCreateTime());
    }

  
}
