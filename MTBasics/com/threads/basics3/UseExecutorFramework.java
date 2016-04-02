package com.threads.basics3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class shows the use of executor framework, explains thread pools along
 * with the use of shutdown, awaitTermination and shutdownNow methods.
 * 
 * @author Sahil Gupta
 *
 */

class runner extends Thread {
	final static int COUNT_END = 1000;
	int TID;
	int count = 0;

	public runner(int tid) {
		TID = tid;
	}

	public void run() {
		System.out.println("Starting thread: " + TID + " with count: " + count);
		for (int i = 0; i < COUNT_END; i++) {
			count++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Ending thread: " + TID + " with count: " + count);
	}
}

public class UseExecutorFramework {

	// Vary the number of threads in the pool i.e. between 0 and N_THREADS_TOTAL
	// to see how executor service completes your threads/tasks.
	final static int N_THREADS_POOL = 10;
	final static int N_THREADS_TOTAL = 100;

	public static void main(String args[]) {
		ExecutorService es = Executors.newFixedThreadPool(N_THREADS_POOL);
		for (int i = 0; i < N_THREADS_TOTAL; i++) {
			Runnable worker = new runner(i);
			es.execute(worker);
		}
		/**
		 * http://stackoverflow.com/questions/18425026/shutdown-and-
		 * awaittermination-which-first-call-have-any-difference shutdownNow :
		 * 
		 * Attempts to stop all actively executing tasks, halts the processing
		 * of waiting tasks, and returns a list of the tasks that were awaiting
		 * execution. These tasks are drained (removed) from the task queue upon
		 * return from this method.
		 * 
		 * This method does not wait for actively executing tasks to terminate.
		 * Use awaitTermination to do that.
		 * 
		 * There are no guarantees beyond best-effort attempts to stop
		 * processing actively executing tasks. This implementation cancels
		 * tasks via Thread.interrupt(), so any task that fails to respond to
		 * interrupts may never terminate shutdown:
		 * 
		 * Initiates an orderly shutdown in which previously submitted tasks are
		 * executed, but no new tasks will be accepted. Invocation has no
		 * additional effect if already shut down.
		 * 
		 * This method does not wait for previously submitted tasks to complete
		 * execution. Use awaitTermination to do that. awaitTermination:
		 * 
		 * Blocks until all tasks have completed execution after a shutdown
		 * request, or the timeout occurs, or the current thread is interrupted,
		 * whichever happens first.
		 */
		es.shutdown();
		// Wait until all threads are finish

		try {
			es.awaitTermination(1, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finished all threads");
	}

}
