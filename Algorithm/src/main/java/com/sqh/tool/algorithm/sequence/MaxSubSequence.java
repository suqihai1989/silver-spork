package com.sqh.tool.algorithm.sequence;

/**
 * 给定整数A1, A2,....,An (可能有负数),求最大子序列。<br/>
 * 例 输入: -2, 11, -4, 13, -5, -2时,答案为20(A2 到 A4)
 * @author Administrator
 */
public interface MaxSubSequence {

	public int[] maxSubSequence(int[] input);
}
