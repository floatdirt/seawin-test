/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author lijin
 * @version $Id: TestController.java, v 0.1 2018年10月23日 下午6:21:02 lijin Exp $
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test")
    public ModelAndView myTest() {
        int test = testService.test(10);
        return null;
    }

    @RequestMapping(value = "/test1")
    public ModelAndView myTest1() {
        String yanyi = testService.test1("yanyi");
        return null;
    }
}
