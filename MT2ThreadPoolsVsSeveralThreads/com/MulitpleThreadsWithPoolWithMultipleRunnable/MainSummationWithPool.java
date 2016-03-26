package com.MulitpleThreadsWithPoolWithMultipleRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.Model.SumTask;

public class MainSummationWithPool {
	
	  private static final int NTHREDS = 3;
	  /* IMPORTANT:
	   * if the number of threads is increased from 1 to n let's say
	   * then as n increases, we get diminishing returns. For instance,
	   * n = 1 & Time =  1743306000
	   * n = 2 & Time =  1306772000
	   * n = 3 & Time =  921651000
	   * n = 4 & Time =  946090000
	   * n = 5 & Time =  920146000
	   * n = 10 & Time = 925337000
	   * 
	   * As seen in this example, 3 # of threads is the best solution.
	   * The author had initially used n = 10 but as he mentioned,
	   * with the increasing number of threads, the overhead associated with
	   * them increases. Hence, IT MAKES SENSE TO SAMPLE AND DECIDE WHAT 
	   * NUMBER OF THREADS IS GOOD. ALSO, having same number of threads(workers) as
	   * runnables (tasks), might not be good because of the overhead (context switching,
	   * stacks, etc.) associated with them.
	   */

	  public static void main(String[] args) {
		long startTimeSummationWithPool = System.nanoTime();
	    ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
	    for (int i = 0; i < 500; i++) {
	      Runnable worker = new SumTask(10000000L + i);
	      executor.execute(worker);
	    }
	    // This will make the executor accept no new threads
	    // and finish all existing threads in the queue
	    executor.shutdown();
	    // Wait until all threads are finish
	    try {
			executor.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    System.out.println("Finished all threads");
	    
		long estimatedTime = System.nanoTime() - startTimeSummationWithPool;
		System.out.println("The total time taken by Summation With Pool program is: " + estimatedTime);
	  } 
}
