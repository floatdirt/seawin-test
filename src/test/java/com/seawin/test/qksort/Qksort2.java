/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.qksort;

import com.alibaba.fastjson.JSON;

/**
 * 用Java写算法之五：快速排序  http://blog.51cto.com/flyingcat2013/1281614
 * @author lijin
 * @version $Id: Qksort.java, v 0.1 2018年9月8日 下午5:02:51 lijin Exp $
 */
public class Qksort2 {

    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(int[] arr, int low, int high) {
        if (high <= low)
            return;
        int middle = partition(arr, low, high);
        qsort(arr, low, middle - 1);
        qsort(arr, middle + 1, high);

    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (high > low) {
            while (pivot <= arr[high] && high > low) {
                high--;
            }
            arr[low] = arr[high];
            while (pivot >= arr[low] && high > low) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void main(String[] args) {
        int[] arr = { 7, 3, 5, 8, 6, 12, 10, 4, 8, 7, 6, 8 };
        System.out.println(JSON.toJSONString(arr));
        quickSort(arr);
        System.out.println(JSON.toJSONString(arr));

    }
}
