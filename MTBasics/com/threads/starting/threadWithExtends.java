package com.threads.starting;

/**
 * This class spawns a thread by extending the thread class. Here the attribute
 * i is static and shared by all threads. Run the test cases to see the
 * behavior.
 * 
 * @author Sahil Gupta
 */

class runner extends Thread {
	static int i;
	int threadCount;

	public runner(int j, int threadCount) {
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
 * This class spawns a thread by extending the thread class. Here the attribute
 * i is separate for all objects. Run the test cases to see the behavior.
 * 
 * @author Sahil Gupta
 */

class runner1 extends Thread {
	int i;
	int threadCount;

	public runner1(int j, int threadCount) {
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

public class threadWithExtends {

	public void case1() {
		runner r1 = new runner(0, 1);
		runner r2 = new runner(50, 2);

		r1.start();
		r2.start();
	}

	public void case2() {
		runner1 r3 = new runner1(0, 3);
		runner1 r4 = new runner1(50, 4);

		r3.start();
		r4.start();
	}

	public static void main(String args[]) {
		threadWithExtends tx = new threadWithExtends();
		tx.case1();
		// tx.case2();
	}
}
