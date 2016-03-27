package com.sqh.tool.algorithm.sequence;

/**
 * 分治，求左、右的最大子序列，再求中间边界的最大子序列.<br/>
 * 求三个的Max即可。
 * @author Administrator
 *
 */
public class DivideAndConQuer implements MaxSubSequence {

	public int[] maxSubSum(int[] a, int left, int right){
		if(left == right){  /* Base Case */
			if(a[left] > 0){
				return new int[]{left, right, a[left]};
			}else{
				return new int[]{-1, -1, 0};
			}
		}
		
		int center = (left + right)/2;
		int[] maxLeftSum = maxSubSum(a, left, center);
		int[] maxRightSum = maxSubSum(a, center + 1, right);
		
		int[] maxLeftBorderSum = new int[]{center, center, 0}; 
		int leftBorderSum = 0;
		for(int i = center; i >= left; i--){
			leftBorderSum += a[i];
			if(leftBorderSum > maxLeftBorderSum[2]){
				maxLeftBorderSum[2] = leftBorderSum;
				maxLeftBorderSum[0] = i;
			}
		}
		
		int[] maxRightBorderSum = new int[]{center+1, center+1, 0}; 
		int rightBorderSum = 0;
		for(int i = center+1; i <= right; i++){
			rightBorderSum += a[i];
			if(rightBorderSum > maxRightBorderSum[2]){
				maxRightBorderSum[2] = rightBorderSum;
				maxLeftBorderSum[1] = i;
			}
		}
		
		int maxCenterSum = maxLeftBorderSum[2] + maxRightBorderSum[2];
		
		if(maxLeftSum[2] > maxRightSum[2]){
			if(maxLeftSum[2] > maxCenterSum){
				return maxLeftSum;
			}else{
				return new int[]{maxLeftBorderSum[0], maxRightBorderSum[1], maxCenterSum};
			}
		}else{
			if(maxRightSum[2] > maxCenterSum){
				return maxRightSum;
			}else{
				return new int[]{maxLeftBorderSum[0], maxRightBorderSum[1], maxCenterSum};
			}
		}
	}
	
	@Override
	public int[] maxSubSequence(int[] input) {
		return maxSubSum(input, 0, input.length - 1);
	}

	public static void main(String[] args){
		int[] a = new int[]{-2, 11, -4, 13, -5, -2};
		MaxSubSequence algo = new DivideAndConQuer();
		int[] ret = algo.maxSubSequence(a);
		for(int i=0; i<ret.length; i++){
			System.out.println(ret[i]);
		}
	}
}
