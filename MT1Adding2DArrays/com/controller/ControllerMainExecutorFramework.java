package com.controller;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.multiThreaded.ArraySum2D;
import com.sequentialNonMT.ArraySum2DNonMT;

public class ControllerMainExecutorFramework {

	private final static int cols = 2000;
	private final static int rows = 1000;
	private static volatile int[][] arrayToAdd = new int[rows][cols];
	private static Random rand = new Random();
	private static ArraySum2D a0, a1, a2, a3;
	private final static int nThreads = 4;

	public static void main(String[] args) throws InterruptedException {
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				arrayToAdd[j][i] = rand.nextInt(100);
			}
		}

		ArraySum2DNonMT a = new ArraySum2DNonMT(arrayToAdd);

		long startTimeSequential = System.nanoTime();
		a.runSequential();
		long estimatedTimeSequential = System.nanoTime() - startTimeSequential;

		System.out
				.println("The total sum calculated by sequential program is:       "
						+ a.getTotalSum());
		System.out
				.println("The total time taken by sequential program is:           "
						+ estimatedTimeSequential);

		CountDownLatch cLatch = new CountDownLatch(4);

		a0 = new ArraySum2D(arrayToAdd, 0, cLatch);
		a1 = new ArraySum2D(arrayToAdd, 1, cLatch);
		a2 = new ArraySum2D(arrayToAdd, 2, cLatch);
		a3 = new ArraySum2D(arrayToAdd, 3, cLatch);

		ExecutorService executor = Executors.newFixedThreadPool(nThreads, Executors.defaultThreadFactory());
		
		/*
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		Executors.defaultThreadFactory: ThreadFactory can create threads/workers on the fly to
		execute tasks/runnables.
		*/
		
		long startTimeMultiThreaded = System.nanoTime();
		executor.submit(a0);
		executor.submit(a1);
		executor.submit(a2);
		executor.submit(a3);
		
		cLatch.await();
		/*
		Since CountDownLatch is being used, I don't need executor to wait till
		termination of all the threads.
		NOTE:CountDownLatches are synchronized and thread safe. Very good and easy
		class to use. This is one scenario.
		*/
		executor.shutdown();
		//executor.awaitTermination(1, TimeUnit.DAYS);
		BigInteger Sum = addThreadSum();
		long estimatedTimeMultiThreaded = System.nanoTime()
				- startTimeMultiThreaded;

		System.out
				.println("The total sum calculated by multi threaded program is:   "
						+ Sum);
		System.out
				.println("The total time taken by multi threaded program is:       "
						+ estimatedTimeMultiThreaded);
	}

	private static BigInteger addThreadSum() {
		return a0.getTotalSum().add(a1.getTotalSum()).add(a2.getTotalSum())
				.add(a3.getTotalSum());
	}

}
