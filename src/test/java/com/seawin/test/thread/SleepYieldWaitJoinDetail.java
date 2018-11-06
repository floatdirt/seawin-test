/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.thread;

/**
 * 
 * @author lijin
 * @version $Id: SleepYieldWaitJoin.java, v 0.1 2018年10月24日 下午3:20:05 lijin Exp $
 */
public class SleepYieldWaitJoinDetail {
    /**
     * 
     * 参考https://blog.csdn.net/xiangwanpeng/article/details/54972952
     * sleep() 
    sleep()方法需要指定等待的时间，它可以让当前正在执行的线程在指定的时间内暂停执行，进入阻塞状态，该方法既可以让其他同优先级或者高优先级的线程得到执行的机会，也可以让低优先级的线程得到执行机会。但是sleep()方法不会释放“锁标志”，也就是说如果有synchronized同步块，其他线程仍然不能访问共享数据。 
    
    wait() 
    　　wait()方法需要和notify()及notifyAll()两个方法一起介绍，这三个方法用于协调多个线程对共享数据的存取，所以必须在synchronized语句块内使用，也就是说，调用wait()，notify()和notifyAll()的任务在调用这些方法前必须拥有对象的锁。注意，它们都是Object类的方法，而不是Thread类的方法。 
    　　wait()方法与sleep()方法的不同之处在于，wait()方法会释放对象的“锁标志”。当调用某一对象的wait()方法后，会使当前线程暂停执行，并将当前线程放入对象等待池中，直到调用了notify()方法后，将从对象等待池中移出任意一个线程并放入锁标志等待池中，只有锁标志等待池中的线程可以获取锁标志，它们随时准备争夺锁的拥有权。当调用了某个对象的notifyAll()方法，会将对象等待池中的所有线程都移动到该对象的锁标志等待池。 
    　　除了使用notify()和notifyAll()方法，还可以使用带毫秒参数的wait(long timeout)方法，效果是在延迟timeout毫秒后，被暂停的线程将被恢复到锁标志等待池。 
    　　此外，wait()，notify()及notifyAll()只能在synchronized语句中使用，但是如果使用的是ReenTrantLock实现同步，该如何达到这三个方法的效果呢？解决方法是使用ReenTrantLock.newCondition()获取一个Condition类对象，然后Condition的await()，signal()以及signalAll()分别对应上面的三个方法。
    
    yield() 
    　　yield()方法和sleep()方法类似，也不会释放“锁标志”，区别在于，它没有参数，即yield()方法只是使当前线程重新回到可执行状态，所以执行yield()的线程有可能在进入到可执行状态后马上又被执行，另外yield()方法只能使同优先级或者高优先级的线程得到执行机会，这也和sleep()方法不同。
    
    join() 
    　　join()方法会使当前线程等待调用join()方法的线程结束后才能继续执行，例如：

     */
    /**
     * 参考https://blog.csdn.net/ForWayfarer/article/details/3455130?utm_source=blogxgwz0
     * 1.sleep()和wait()
            这两个方法都可以让调用它的线程沉睡(sleep)/停止运行(wait)指定的时间，到了这个时间，线程就会自动醒来，变为可运行状态（RUNNABLE）。

            public static native void sleep(long millis) throws InterruptedException;   
            public static void sleep(long millis, int nanos) throws InterruptedException 

            public final void wait() throws InterruptedException
            public final native void wait(long timeout) throws InterruptedException;   
            public final void wait(long timeout, int nanos) throws InterruptedException 

            Parameters:
            millis - the length of time to sleep in milliseconds.毫秒数
            nanos - 0-999999 additional nanoseconds to sleep.纳秒数

            调用sleep()方法并不会让线程释放它所持有的同步锁；而且在这期间它也不会阻碍其它线程的运行。
            当调用了某个对象的wait()方法时，当前运行的线程就会转入WAITING状态，等待别的线程再次调用这个对象的notify()或者notifyAll()方法唤醒它，或者到了指定的最大等待时间，线程自动醒来。如果线程调用了某个对象的wait()方法，这个线程就会释放这个对象所持有的同步资源(不会释放其他对象的同步锁)。jdk api doc：The current thread must own this object's monitor. The thread releases ownership of this monitor and waits until another thread notifies threads waiting on this object's monitor to wake up either through a call to the notify method or the notifyAll method
     */

}
