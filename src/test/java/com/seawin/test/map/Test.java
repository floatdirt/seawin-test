/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.map;

/**
  * @author Jone Hongten
  * @create date：2013-11-2  
  * * @version 1.0
https://www.cnblogs.com/hongten/p/hongten_java_yiweiyunsuangfu.html
  */
public class Test {

    public static void main(String[] args) {
        int number = 10;
        //原始数二进制
        printInfo(number);
        //左移一位
        //        number = number << 1;
        //        printInfo(number);

        //右移一位
        number = number >> 1;
        printInfo(number);
    }

    /**
     * 输出一个int的二进制数
     * @param num
     */
    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }
}
