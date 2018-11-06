package com.seawin.test.threadPool;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义简单线程池
 */
public class MyThreadPool {

	/** 存放线程的集合 */
	private ArrayList<MyThead> threads;
	/** 任务队列 */
	private ArrayBlockingQueue<Runnable> taskQueue;
	/** 线程池初始限定大小 */
	private int threadNum;
	/** 已经工作的线程数目 */
	private int workThreadNum;
	
	private final ReentrantLock mainLock = new ReentrantLock();

	/**
	 * 用一个线程去取队列中的线程并且启动
	 */
	// private void startPool(){
	// workingThread = new Runnable() {
	// @SuppressWarnings("static-access")
	// @Override
	// public void run() {
	// for(;;){
	// if (workThreadNum < threadNum) {
	// Runnable task = taskQueue.poll();
	// if (task != null) {
	// task.run();
	// workThreadNum++;
	// }
	// }
	// }
	// }
	// };
	// new Thread(workingThread).start();
	// }

	public MyThreadPool(int initPoolNum) {
		threadNum = initPoolNum;
		threads = new ArrayList<MyThead>(initPoolNum);
		// 任务队列初始化为线程池线程数的四倍
		taskQueue = new ArrayBlockingQueue<Runnable>(initPoolNum * 4);
		threadNum = initPoolNum;
		workThreadNum = 0;
	}

	public void execute(Runnable runnable) {
		try {
			mainLock.lock();
			// 线程池未满，每加入一个任务则开启一个线程
			if (workThreadNum < threadNum) {
				MyThead myThead = new MyThead(runnable);
				myThead.start();
				threads.add(myThead);
				workThreadNum++;
			}
			// 线程池已满，放入任务队列，等待有空闲线程时执行
			else {
				// 队列已满，无法添加时，拒绝任务
				if (!taskQueue.offer(runnable)) {
					rejectTask();
				}
			}
		} finally {
			mainLock.unlock();
		}
	}

	private void rejectTask() {
		System.out.println("任务队列已满，无法继续添加，请扩大您的初始化线程池！");
	}
	public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(1);
        final CountDownLatch cdl = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            myThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //                        cdl.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(j + "执行中");
                }
            });

        }
        cdl.countDown();

        /*myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("2" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("3" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("4" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("5" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("6" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("7" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("8" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("9" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("10" + "执行中");
        	}
        });
        myThreadPool.execute(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("11" + "执行中");
        	}
        });*/
        /*	ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 10,
        			TimeUnit.SECONDS, null);*/
	}

	class MyThead extends Thread {
		private Runnable task;

		public MyThead(Runnable runnable) {
			this.task = runnable;
		}

		@Override
		public void run() {
			// 该线程一直启动着，不断从任务队列取出任务执行
			while (true) {
				// 如果初始化任务不为空，则执行初始化任务
				if (task != null) {
					task.run();
					task = null;
				}
				// 否则去任务队列取任务并执行
				else {
					Runnable queueTask = taskQueue.poll();
					if (queueTask != null)
						queueTask.run();
				}
			}
		}
	}

}
