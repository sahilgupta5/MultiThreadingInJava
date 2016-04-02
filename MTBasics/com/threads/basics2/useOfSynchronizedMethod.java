package com.threads.basics2;

/**
 * This class shows the use of synchronized keyword which helps in solving the
 * accessibility concurrency problem i.e. An access problem can occur if several
 * thread access and change the same shared data at the same time.
 * 
 * The synchronized keyword in Java ensures:
 * 
 * 1. that only a single thread can execute a block of code at the same time
 * 
 * 2. that each thread entering a synchronized block of code sees the effects of
 * all previous modifications that were guarded by the same lock
 * 
 * Toggle between the synchronized incrementCounter method i.e.
 * syncIncrementCounter and incrementCounter to see the use.
 * 
 * @author Sahil Gupta
 *
 */

public class useOfSynchronizedMethod {

	static int counter = 0;

	public synchronized static void syncIncrementCounter() {
		counter++;
	}

	public static void incrementCounter() {
		counter++;
	}

	static final int END = 100000;

	public static void main(String args[]) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < END; i++) {
					// incrementCounter();
					syncIncrementCounter();
				}
				System.out.println("Thread 1 counter: " + counter);
			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < END; i++) {
					// incrementCounter();
					syncIncrementCounter();
				}
				System.out.println("Thread 2 counter: " + counter);
			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
