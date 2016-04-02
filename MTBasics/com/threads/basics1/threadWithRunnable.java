package com.threads.basics1;

/**
 * This class spawns a thread by implementing the Runnable interface. Here the
 * attribute i is static and shared by all threads. Run the test cases to see
 * the behavior.
 * 
 * @author Sahil Gupta
 */

class runnableRunner implements Runnable {
	static int i;
	int threadCount;

	public runnableRunner(int j, int threadCount) {
		i = j;
		this.threadCount = threadCount;
		System.out.println("Hello. Thread count: " + threadCount
				+ " Start Counter: " + j);
	}

	public void run() {
		long time = System.currentTimeMillis();
		for (; i < 100; i++) {
			System.out.println("Hello. Thread count: " + threadCount
					+ " Counter: " + i);
		}
		System.out.println("Time taken " + "for thread with count: "
				+ threadCount + " is: " + (System.currentTimeMillis() - time));
	}
}

/**
 * This class spawns a thread by implementing the Runnable interface. Here the
 * attribute i is separate for all objects. Run the test cases to see the
 * behavior.
 * 
 * @author Sahil Gupta
 */

class runnableRunner1 implements Runnable {
	int i;
	int threadCount;

	public runnableRunner1(int j, int threadCount) {
		this.i = j;
		this.threadCount = threadCount;
		System.out.println("Hello. Thread count: " + threadCount
				+ " Start Counter: " + j);
	}

	public void run() {
		long time = System.currentTimeMillis();
		for (; i < 100; i++) {
			System.out.println("Hello. Thread count: " + threadCount
					+ " Counter: " + i);
		}
		System.out.println("Time taken " + "for thread with count: "
				+ threadCount + " is: " + (System.currentTimeMillis() - time));
	}
}

public class threadWithRunnable {

	public void case1() {
		Thread r1 = new Thread(new runnableRunner(0, 1));
		Thread r2 = new Thread(new runnableRunner(50, 2));

		r1.start();
		r2.start();
	}

	public void case2() {
		Thread r3 = new Thread(new runnableRunner1(0, 3));
		Thread r4 = new Thread(new runnableRunner1(50, 4));

		r3.start();
		r4.start();
	}

	public static void main(String args[]) {
		threadWithRunnable tx = new threadWithRunnable();
		// tx.case1();
		 tx.case2();
	}
}