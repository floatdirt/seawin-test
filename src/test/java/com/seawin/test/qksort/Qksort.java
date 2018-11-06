/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.qksort;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author lijin
 * @version $Id: Qksort.java, v 0.1 2018年9月8日 下午5:02:51 lijin Exp $
 */
public class Qksort {

    public static void partition(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int lm = low, hm = high;
        int key = arr[low];
        for (; lm != hm;) {
            if (arr[lm] > arr[hm]) {
                int temp = arr[hm];
                arr[hm] = arr[lm];
                arr[lm] = temp;
                if (key == arr[hm]) {
                    lm++;
                } else {
                    hm--;
                }
            } else {
                if (key == arr[lm]) {
                    hm--;
                } else {
                    lm++;
                }

            }

        }
        partition(arr, low, lm - 1);
        partition(arr, lm + 1, high);
    }

    public static void sort(int[] arr) {
        partition(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = { 7, 3, 5, 6, 4, 55, 34, 23, 45, 23, 45, 23, 45, 23, 13, 3, 5, 12, 43, 4, 33,
                44, 22, 33 };
        System.out.println(JSON.toJSONString(arr));
        sort(arr);
        System.out.println(JSON.toJSONString(arr));

    }
}
