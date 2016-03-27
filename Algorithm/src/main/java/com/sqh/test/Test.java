package com.sqh.test;


import java.util.ArrayList;
import java.util.List;

import com.sqh.test.utils.FileUtils;

public class Test {
	private static String OVER_TAG = "55 AA 02 F0 F2"; 
	private static int stepSum = 0;
	private static int climpSum = 0;
	public static void main(String[] args){
		List<String> strs = FileUtils.getContent(null, "E:\\data.txt");
		List<String> hexStrs = new ArrayList<String>();
		int i = 0;
		int k = 0;
		for(String str : strs){
			if(OVER_TAG.equals(str)){
				break;
			}
			String[] splits;
			if(str.startsWith("55 AA 39 C3 36 09 FF")){
				k = 2;
				continue;
			}
			
			if(k > 0){
				k--;
				continue;
			}
			
			if(str.startsWith("55 AA 39 C3 36")){
				parseData(hexStrs);
				hexStrs.clear();
				splits = str.substring(14).split(" ");
				i = 1;
			}else{
				if(i == 3){
					splits = str.substring(0, str.length()-1).split(" ");
				}
				splits = str.split(" ");
			}
			for(String split : splits){
				if(split.trim().length() > 0)
					hexStrs.add(split);
			}
			i++;
		}
	}

	private static void parseData(List<String> hexStrs){
		StringBuffer sb = new StringBuffer();
		int i = 0;
		String pre = null;
		for(String hexStr : hexStrs){
			String bs = Integer.toBinaryString(Integer.parseInt(hexStr, 16));
			if(bs.length() < 8){
				int bsLen = 8-bs.length();
				for(int j=0; j<bsLen; j++){
					bs = "0" + bs;
				}
			}
			i++;
			if(i%2 == 1){
				sb.append(bs.substring(0, 2)).append(" ").append(bs.substring(2, 7)).append(" ").append(bs.substring(7));
				pre = bs;
			}else{
				sb.append(bs.substring(0, 3)).append(" ").append(bs.substring(3));
				String twoByteString = pre + bs;
				if("0000".equals(twoByteString)){
					sb.append("    ").append("数据结尾, 空值填充");;
				}
				if(twoByteString.startsWith("11")){
					sb.append("    ").append("F4(日期数据): " + getDay(twoByteString.substring(2)));
				}
				if(twoByteString.startsWith("10")){
					sb.append("    ").append("F3(时间节点): " + getTime(twoByteString.substring(2)));
				}
				if(twoByteString.startsWith("0") && twoByteString.endsWith("100111111111")){
					sb.append("    ").append("F2(睡眠数据): " + getSleepData(twoByteString.substring(1, 7)));
				}
				if(twoByteString.startsWith("0") && !twoByteString.endsWith("100111111111")){
					sb.append("    ").append("F1(运动数据): " + getSportData(twoByteString.substring(1)));
				}
				sb.append("\n");
			}
		}
		sb.append("总climpNum : " + climpSum + ", 总stepNum : " + stepSum);
		System.out.println(sb.toString());
	}
	
	private static String getDay(String substring) {
		int year = Integer.parseInt(substring.substring(0, 5), 2) + 2014;
		int month = Integer.parseInt(substring.substring(5, 9), 2);
		int day = Integer.parseInt(substring.substring(9), 2);
		
		return year + "年" + month + "月" + day + "日";
	}

	//解析运动数据
	private static String getSportData(String substring) {
		int climpNum = Integer.parseInt(substring.substring(0, 6), 2);
		int stepNum  = Integer.parseInt(substring.substring(6), 2);
		
		Test.climpSum += climpNum;
		Test.stepSum += stepNum;
		
		return "爬升高度: " + climpNum + ", 步数:" + stepNum;
	}

	//解析睡眠数据
	private static String getSleepData(String substring) {
		return String.valueOf(Integer.parseInt(substring, 2));
	}

	private static String getTime(String substring) {
		int mins = Integer.parseInt(substring, 2);
		StringBuffer sb = new StringBuffer();
		int hours = mins/60;
		int min  = mins%60;
		sb.append(hours < 10 ? ("0" + hours) : hours).append(":").append(min < 10 ? ("0" + min) : min);
		return sb.toString();
	}
}
