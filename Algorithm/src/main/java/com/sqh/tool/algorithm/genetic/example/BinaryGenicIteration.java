package com.sqh.tool.algorithm.genetic.example;

import com.sqh.tool.algorithm.genetic.base.Genic;
import com.sqh.tool.algorithm.genetic.base.GenicIteration;

public class BinaryGenicIteration extends GenicIteration {

	public BinaryGenicIteration(int genicNum, int genicLength) {
		super(genicNum, genicLength);
	}

	@Override
	protected void testAdaptation(Genic genic) {
		int[] bits = genic.getGenic();
		int sum = 0;
		for(int i = 0; i < bits.length; i++){
			if(bits[i] == 1){
				sum++;
			}
		}
		if(sum > bits.length/2){
			genic.setAdaptation(sum*1.0/bits.length);
		}else{
			genic.setAdaptation(sum*0.7/bits.length);
		}
	}

	public static void main(String[] args){
		BinaryGenicIteration b = new BinaryGenicIteration(30, 10);
		b.iteration();
	}
}
