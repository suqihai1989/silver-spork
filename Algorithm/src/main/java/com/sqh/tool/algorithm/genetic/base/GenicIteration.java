package com.sqh.tool.algorithm.genetic.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 遗传算法
 * 关键点:
 * 1. 赌轮选择基因组
 * 2. 杂交系数
 * 3. 变异系数
 * 4. 适应性判断
 * @author Administrator
 *
 */
public abstract class GenicIteration {
	protected List<Genic> initGenics;    //初始种群
	private List<Genic>   generGenics;  //后代种群
	private final int     iterMaxNum = 30;     //迭代次数
	private final double  crossProp = 0.7;     //杂交系数
	private final double  variProp = 0.0001;   //变异系数
	private double 		  sumAdaptation;       //适应性总和, 用来方便赌轮选择。
	private int           genicLength;
	
	/**
	 * 初始种群
	 * @param genicNum
	 * @param genicLength
	 */
	private void initGenics(int genicNum, int genicLength){
		for(int i = 0; i < genicNum; i++){
			int[] genic = new int[genicLength];
			for(int j = 0; j < genicLength; j++){
				if(RandomUtils.randFloat0to1() > 0.5){
					genic[j] = 1;
				}else{
					genic[j] = 0;
				}
			}
			initGenics.add(new Genic(genic, genicLength, 0.0));
		}
	}
	
	public GenicIteration(int genicNum, int genicLength){
		initGenics = new ArrayList<Genic>();
		initGenics(genicNum, genicLength);
		generGenics = new ArrayList<Genic>();
		sumAdaptation = 0.0;
		for(Genic g : initGenics){
			testAdaptation(g);
			sumAdaptation += g.getAdaptation();
		}
		this.genicLength = genicLength;
	}
	
	/**
	 * 进化迭代
	 */
	public void iteration(){
		for(int i = 0; i < iterMaxNum; i++){
			while(generGenics.size() < initGenics.size()){
				Genic genic1 = gambSelect();
				Genic genic2 = gambSelect();
				
				//若随机数小于杂交系数, 则两个后代由杂交产生
				int[] newBits1 = null;
				int[] newBits2 = null;
				if(RandomUtils.randFloat0to1() < crossProp){
					newBits1 = new int[genicLength];
					newBits2 = new int[genicLength];
					int[] bits1 = genic1.getGenic();
					int[] bits2 = genic2.getGenic();
					int index = new Double(RandomUtils.randFloat0to1()*bits1.length).intValue();
					for(int j = 0; j < index; j++){
						newBits1[j] = bits1[j];
						newBits2[j] = bits2[j];
					}
					for(int j = index; j < bits1.length; j++){
						newBits1[j] = bits2[j];
						newBits2[j] = bits1[j];
					}
				}else{
					newBits1 = genic1.getGenic();
					newBits2 = genic2.getGenic();
				}
				
				//对每一位上的数, 进行变异
				for(int j = 0; j < genicLength; j++){
					if(RandomUtils.randFloat0to1() < variProp){
						newBits1[j] = newBits1[j] == 0 ? 1 : newBits1[j];
					}
					if(RandomUtils.randFloat0to1() < variProp){
						newBits2[j] = newBits2[j] == 0 ? 1 : newBits2[j];
					}
				}
				
				generGenics.add(new Genic(newBits1, genicLength, 0.0));
				generGenics.add(new Genic(newBits2, genicLength, 0.0));
			}
			
			sumAdaptation = 0.0;
			for(Genic g : generGenics){
				testAdaptation(g);
				sumAdaptation += g.getAdaptation();
			}
			
			showMes(i);
			initGenics.clear();
			initGenics.addAll(generGenics);
			generGenics.clear();
		}
	}
	
	private void showMes(int i) {
		System.out.println("===============第" + i + "代================");
		for(Genic g : initGenics){
			int[] genic = g.getGenic();
			for(int j = 0; j < genic.length; j++){
				System.out.print(genic[j]);
			}
			System.out.println("    " + g.getAdaptation());
		}
	}

	/**
	 * 赌轮选择基因组
	 * @return
	 */
	private Genic gambSelect(){
		while(true){
			double randomDouble = RandomUtils.randFloat0to1()*sumAdaptation;
			double sum = 0.0;
			for(Genic g : initGenics){
				sum += g.getAdaptation();
				if(sum > randomDouble){
					return g;
				}
			}
		}
	}
	
	/**
	 * 测试基因的适应性
	 * @param genic
	 */
	protected abstract void testAdaptation(Genic genic);

	public List<Genic> getInitGenics() {
		return initGenics;
	}
}
