package com.controller;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import com.multiThreaded.ArraySum2D;
import com.sequentialNonMT.ArraySum2DNonMT;

public class ControllerMain {

	private final static int cols = 2000;
	private final static int rows = 1000;
	private static volatile int[][] arrayToAdd = new int[rows][cols];
	private static Random rand = new Random();
	private static ArraySum2D a0, a1, a2, a3;

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

		Thread t0 = new Thread(a0);
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a2);
		Thread t3 = new Thread(a3);

		long startTimeMultiThreaded = System.nanoTime();
		t0.start();
		t1.start();
		t2.start();
		t3.start();
		cLatch.await();

//		t0.join();
//		t1.join();
//		t2.join();
//		t3.join();
		
		/*
		 * Now instead of using joins, using thread safe and synchronized CountDownLatch
		 * is a lot better.
		 */
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
