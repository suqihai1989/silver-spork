package com.sqh.tool.algorithm.math;

/**
 * 定理：两个整数的最大公约数等于其中较小的那个数和两数的相除余数的最大公约数。最大公约数（greatest common divisor）缩写为gcd。
	gcd(a,b) = gcd(b,a mod b) (不妨设a>b 且r=a mod b ,r不为0)
证法一
	a可以表示成a = kb + r（a，b，k，r皆为正整数），则r = a mod b
	假设d是a,b的一个公约数，记作d|a,d|b，即a和b都可以被d整除。
	而r = a - kb，两边同时除以d，r/d=a/d-kb/d=m，等式左边可知m为整数，因此d|r
	因此d也是（b,a mod b）的公约数
	因此（a,b）和（b,a mod b）的公约数是一样的，其最大公约数也必然相等，得证。
 * @author Administrator
 *
 */
public class EuclideanGcd {
	public static int getGcd(int m, int n){
		if (m % n == 0) {
			return n;
		} else {
			return getGcd(n, m % n);
		}
	}
	
	public static void main(String[] args){
		System.out.println(EuclideanGcd.getGcd(6, 2));
		System.out.println(EuclideanGcd.getGcd(2, 6));
	}
}
