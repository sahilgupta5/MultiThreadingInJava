package com.multiThreaded;

import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author Sahil Gupta
 * 
 *         This class takes in a 2D integer array and adds it's contents. This
 *         addition will be concurrent between several threads which will divide
 *         the work of the array based on the threadID assigned to thread by the
 *         programmer. Assume that the passed in 2D array to the constructor is
 *         a matrix with each array in the main array having same length.
 * 
 *         Example 2D array - This will be generated randomly. This illustration
 *         shows division of work by different threads. Here 4 threads will add
 *         the sum individually and then return the sum. The sum from these
 *         threads shall be added once all threads are done.
 * 
 *         ArrayLength = 22 columns and 12 rows
 * 
 *         ---------ID = 0--------------ID = 1------------ 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 |
 *         |--------ID = 2--------------ID = 3-----------| 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 | 
 *         |1 2 7 4 7 8 4 2 9 0 2 |1 2 7 4 7 8 4 2 9 0 2 |
 *         |----------------------------------------------
 * 
 *         column start = (ID%2) * (arrayCol/2) row start = Floor(ID/2) *
 *         (arrayRow/2)
 * 
 * @param <T>
 */
public class ArraySum2D implements Runnable {

	private int[][] arrayToSum;
	private int threadID;
	private BigInteger totalSum;
	private CountDownLatch cLatch;

	public ArraySum2D(int[][] arr, int threadID, CountDownLatch cLatch) {
		this.arrayToSum = arr;
		this.threadID = threadID;
		this.setTotalSum(BigInteger.ZERO);
		this.cLatch = cLatch;
	}

	@Override
	public void run() {
		int arrayCol = arrayToSum[0].length;
		int arrayRow = arrayToSum.length;
		int colStart = (int) ((threadID % 2) * (arrayCol / 2));
		int rowStart = (int) ((int) (threadID / 2) * (arrayRow / 2));
		int colEnd = colStart + (int) (arrayCol / 2);
		int rowEnd = rowStart + (int) (arrayRow / 2);
		
		for (int j = rowStart; j < rowEnd; j++) {
			for (int i = colStart; i < colEnd; i++) {
				setTotalSum(getTotalSum().add(new BigInteger(new Integer(arrayToSum[j][i]).toString())));
			}
		}
		cLatch.countDown();
	}

	public BigInteger getTotalSum() {
		return totalSum;
	}

	private void setTotalSum(BigInteger totalSum) {
		this.totalSum = totalSum;
	}

}
