package com.sequentialNonMT;

import java.math.BigInteger;

/**
 * @author Sahil Gupta
 * 
 *         This class adds the elements of the 2D array in a sequential manner.
 */

public class ArraySum2DNonMT {

	private int[][] arrayToSum;
	private BigInteger totalSum;

	public ArraySum2DNonMT(int[][] arr) {
		this.arrayToSum = arr;
		this.setTotalSum(BigInteger.ZERO);
	}

	public void runSequential() {
		for (int j = 0; j < arrayToSum.length; j++) {
			for (int i = 0; i < arrayToSum[0].length; i++) {
				setTotalSum(getTotalSum().add(new BigInteger(new Integer(arrayToSum[j][i]).toString())));
			}
		}
	}

	public BigInteger getTotalSum() {
		return totalSum;
	}

	private void setTotalSum(BigInteger totalSum) {
		this.totalSum = totalSum;
	}

}
