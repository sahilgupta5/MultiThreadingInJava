package com.threads.basics2;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class shows the use of multiple locks in synchornized code blocks to
 * improve the speed of execution vs. using a single intrinsic lock of the
 * object when used with synchronized methods.
 * 
 * Toggle the commented code to see the difference.
 * 
 * @author Sahil Gupta
 *
 */

public class UseOfSynchronizedBlocks {

	private ArrayList<Integer> a1 = new ArrayList<Integer>();
	private ArrayList<Integer> a2 = new ArrayList<Integer>();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	final int NUM_PROC = 1000;
	final int SLEEP_PROC = 1;

	// public synchronized void step1() throws InterruptedException,
	// InstantiationException, IllegalAccessException {
	// Thread.sleep(SLEEP_PROC);
	// a1.add(Random.class.newInstance().nextInt());
	// }
	//
	// public synchronized void step2() throws InterruptedException,
	// InstantiationException, IllegalAccessException {
	// Thread.sleep(SLEEP_PROC);
	// a2.add(Random.class.newInstance().nextInt());
	// }

	public void step1() throws InterruptedException, InstantiationException,
			IllegalAccessException {
		synchronized (lock1) {
			Thread.sleep(SLEEP_PROC);
			a1.add(Random.class.newInstance().nextInt());
		}
	}

	public void step2() throws InterruptedException, InstantiationException,
			IllegalAccessException {
		synchronized (lock2) {
			Thread.sleep(SLEEP_PROC);
			a2.add(Random.class.newInstance().nextInt());
		}
	}

	public void process() {
		try {
			for (int i = 0; i < NUM_PROC; i++) {
				step1();
				step2();
			}
		} catch (InstantiationException | IllegalAccessException
				| InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		// useOfSynchronizedBlocks usb = new useOfSynchronizedBlocks();
		// System.out.println("Start ...");
		// long timeStart = System.currentTimeMillis();
		// usb.process();
		// System.out.println("Took: " + (System.currentTimeMillis() -
		// timeStart));
		// System.out.println("Size List 1: " + usb.a1.size() + " Size List 2: "
		// + usb.a2.size());
		// System.out.println("Done ...\n");

		final UseOfSynchronizedBlocks usb1 = new UseOfSynchronizedBlocks();
		System.out.println("Start ...");
		long timeStart1 = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				usb1.process();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				usb1.process();
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
		System.out
				.println("Took: " + (System.currentTimeMillis() - timeStart1));
		System.out.println("Size List 1: " + usb1.a1.size() + " Size List 2: "
				+ usb1.a2.size());
		System.out.println("Done ...");
	}
}