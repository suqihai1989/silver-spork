package com.sqh.tool.algorithm.genetic.base;

/**
 * 基因结构定义
 * @author Administrator
 */
public class Genic {
	private int[] genic;       //基因组
	private int   genicLength;   //基因长度
	private double adaptation;    //适应度
	
	public Genic(int[] genic, int genicLength, double adaptation){
		this.genic = genic;
		this.genicLength = genicLength;
		this.adaptation = adaptation;
	}
	
	public int[] getGenic() {
		return genic;
	}
	public void setGenic(int[] genic) {
		this.genic = genic;
	}
	public int getGenicLength() {
		return genicLength;
	}
	public void setGenicLength(int genicLength) {
		this.genicLength = genicLength;
	}
	public double getAdaptation() {
		return adaptation;
	}
	public void setAdaptation(double adaptation) {
		this.adaptation = adaptation;
	}
}
