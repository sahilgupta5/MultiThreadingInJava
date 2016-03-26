package com.MulitpleThreadsWithoutPoolWithMultipleRunnable;

import java.util.ArrayList;
import java.util.List;

import com.Model.SumTask;

public class MainSummationWithoutPool {
	public static void main(String[] args) {
		long startTimeSummationWithoutPool = System.nanoTime();
		// We will store the threads so that we can check if they are done
		List<Thread> threads = new ArrayList<Thread>();
		// We will create 500 threads
		for (int i = 0; i < 500; i++) {
			Runnable task = new SumTask(10000000L + i);
			Thread worker = new Thread(task);
			// We can set the name of the thread
			worker.setName(String.valueOf(i));
			// Start the thread, never call method run() direct
			worker.start();
			// Remember the thread for later usage
			threads.add(worker);
		}
		
		long estimatedTime = System.nanoTime() - startTimeSummationWithoutPool;
		System.out.println("The total time taken by Summation Without Pool program is: " + estimatedTime);

		/*
		int running = 0;
		do {
			running = 0;
			for (Thread thread : threads) {
				if (thread.isAlive()) {
					running++;
				}
			}
			System.out.println("We have " + running + " running threads. ");
		} while (running > 0);
		*/
	}
}