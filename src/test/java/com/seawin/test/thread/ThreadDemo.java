/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.thread;

/**
 * 
 * @author lijin
 * @version $Id: ThreadTest.java, v 0.1 2018年11月6日 上午9:06:27 lijin Exp $
 */

/*
 * 参考http://www.importnew.com/20584.html
 * 1、在第二种方法（Runnable）中，ticket输出的顺序并不是54321，这是因为线程执行的时机难以预测，ticket–并不是原子操作。

    2、在第一种方法中，我们new了3个Thread对象，即三个线程分别执行三个对象中的代码，因此便是三个线程去独立地完成卖票的任务；而在第二种方法中，
            我们同样也new了3个Thread对象，但只有一个Runnable对象，3个Thread对象共享这个Runnable对象中的代码，因此，便会出现3个线程共同完成卖票任务的结果。
            如果我们new出3个Runnable对象，作为参数分别传入3个Thread对象中，那么3个线程便会独立执行各自Runnable对象中的代码，即3个线程各自卖5张票。
    
    3、在第二种方法中，由于3个Thread对象共同执行一个Runnable对象中的代码，因此可能会造成线程的不安全，比如可能ticket会输出-1
            （如果我们System.out….语句前加上线程休眠操作，该情况将很有可能出现），这种情况的出现是由于，
            一个线程在判断ticket为1>0后，还没有来得及减1，另一个线程已经将ticket减1，变为了0，那么接下来之前的线程再将ticket减1，
            便得到了-1。这就需要加入同步操作（即互斥锁），确保同一时刻只有一个线程在执行每次for循环中的操作。而在第一种方法中，并不需要加入同步操作，
            因为每个线程执行自己Thread对象中的代码，不存在多个线程共同执行同一个方法的情况。
*/
public class ThreadDemo {
    public static void main(String[] args) {

        //        new MyThread().start();
        //        new MyThread().start();
        //        new MyThread().start();

        MyRunnable my = new MyRunnable();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
    }
}

class MyThread extends Thread {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}

class MyRunnable implements Runnable {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}
