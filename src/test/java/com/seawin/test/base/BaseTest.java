package com.seawin.test.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class BaseTest {

    // 打印返回
    public void printResult(Object result, String methodName, boolean isPrint) {
        if (isPrint) {
            System.out.println("=======================" + methodName + "=======================");
            System.out.println(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat));
            System.out.println("==============================================");
            /*
            LogUtil.info(logger, "====================================================");
            // SerializerFeature.WriteMapNullValue，是否输出值为null的字段,默认为false 
            // SerializerFeature.PrettyFormat 带格式
            LogUtil.info(logger, JSON.toJSONString(result, SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat));
            LogUtil.info(logger, "====================================================");
            */
        }
    }

}
