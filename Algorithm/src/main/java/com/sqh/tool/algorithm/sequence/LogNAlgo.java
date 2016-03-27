package com.sqh.tool.algorithm.sequence;

public class LogNAlgo implements MaxSubSequence {

	@Override
	public int[] maxSubSequence(int[] input) {
		int tempSum = 0;
		int maxSum = 0;
		int tempLeft = 0;
		
		int left = 0;
		int right = 0;
		for(int i = 0; i < input.length; i++){
			tempSum += input[i];
			if(tempSum > maxSum){
				maxSum = tempSum;
				left = tempLeft;
				right = i;
			}else if(tempSum < 0){  //重新计算新起始位的子串。
				tempSum = 0;
				tempLeft = i+1;
				right = i+1;
			}
		}
		return new int[]{left, right, maxSum};
	}

	public static void main(String[] args){
		int[] a = new int[]{-2, 11, -4, 13, -5, -2};
		MaxSubSequence algo = new LogNAlgo();
		int[] ret = algo.maxSubSequence(a);
		for(int i=0; i<ret.length; i++){
			System.out.println(ret[i]);
		}
	}
}
