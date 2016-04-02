package com.threads.basics2;

/**
 * This class shows the use of volatile keyword where a common long variable is
 * read and incremented by multiple threads whose number is defined by constant
 * TOTAL_THREADS. In the comments throughtout code, toggle the comments to
 * explore the use of volatile keyword in action.
 * 
 * @author Sahil Gupta
 */

class longData {

	// public long counter;
	public volatile long counter;

	public longData(long count) {
		counter = count;
	}

	public long getCounter() {
		return counter;
	}

	public void increaseCounter() {
		++counter;
	}
}

class longThread extends Thread {

	private final longData l;

	public longThread(longData longVolatileData) {
		l = longVolatileData;
	}

	public void run() {
		long oldVal = l.getCounter();
		System.out.println("[Thread " + Thread.currentThread().getId()
				+ "]: Old value = " + oldVal);
		l.increaseCounter();
		long newValue = l.getCounter();
		System.out.println("[Thread " + Thread.currentThread().getId()
				+ "]: New value = " + newValue);

	}

}

public class UseOfVolatile {
	private final static int TOTAL_THREADS = 2;

	public static void main(String[] args) throws InterruptedException {
		// Change the value inside the long data to something different

		longData volatileData = new longData(100004465465470l);
		Thread[] threads = new Thread[TOTAL_THREADS];
		int runNum = 0;

		/*
		 * vary the nnumber of runs to see that threads may start using cached
		 * value on powerful processors with larger caches, this will be
		 * extremely hard to notice but you may get lucky by increasing the
		 * number of runs and using basic math. HINT: Odd will remain odd after
		 * after 2 and even will remain even after adding 2.
		 */

		while (runNum < 1000000) {
			for (int i = 0; i < TOTAL_THREADS; ++i)
				threads[i] = new longThread(volatileData);
			// Start all reader threads.
			for (int i = 0; i < TOTAL_THREADS; ++i)
				threads[i].start();
			// Wait for all threads to terminate.
			for (int i = 0; i < TOTAL_THREADS; ++i)
				threads[i].join();
			runNum++;
			System.out.println("\n\n");
		}
	}

}