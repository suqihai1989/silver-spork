package com.sqh.tool.algorithm.bayes;

import java.util.Map;

public class Category {
	Sample[] samples;
	Map<Feature, Double> featureProbabilities;
	
	public double getFeatureProbInCategory(Feature feature){
		return featureProbabilities.get(feature);
	}
}
