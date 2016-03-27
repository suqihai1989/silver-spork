package com.sqh.test;


import java.util.ArrayList;
import java.util.List;

import com.sqh.test.utils.FileUtils;

public class Test2 {
	private static String OVER_TAG = "55 AA 02 F0 F2"; 
	private static int stepSum = 0;
	private static int climpSum = 0;
	public static void main(String[] args){
		List<String> strs = FileUtils.getContent(null, "E:\\data.txt");
		List<String> twoByteStrings = new ArrayList<String>();
		for(String str : strs){
			if(OVER_TAG.equals(str)){
				break;
			}
			String[] splits = str.split(" ");
			if(splits.length < 3){
				System.out.println("数据异常, 这个数据长度小于3个字节");
			}
			for(int byteIndex = 5; byteIndex < splits.length-1; ){
				twoByteStrings.add(splits[byteIndex] + splits[byteIndex + 1]);
				byteIndex += 2;
			}
			
			parseData(twoByteStrings);
			twoByteStrings.clear();
		}
	}

	private static void parseData(List<String> hexStrs){
		StringBuffer sb = new StringBuffer();
		String firstByte = null;
		String secondByte = null;
		String firstBinaryStr = null;
		String secondBinaryStr = null;
		String twoBinaryStr = null;
		for (String hexStr : hexStrs) {
			firstByte = hexStr.substring(0, 2);
			secondByte = hexStr.substring(2);
			sb.append(firstByte).append(" ").append(secondByte).append(" ");
			
			if ("0000".equals(hexStr)) {
				sb.append("    ").append("数据结尾, 空值填充").append("\n");
				continue;
			}
			
			firstBinaryStr = getBinaryStrFromHexStr(firstByte);
			secondBinaryStr = getBinaryStrFromHexStr(secondByte);
			twoBinaryStr = firstBinaryStr + secondBinaryStr;

			sb.append(firstBinaryStr.substring(0, 2)).append(" ")
					.append(firstBinaryStr.substring(2, 7)).append(" ");
			sb.append(firstBinaryStr.substring(7))
					.append(secondBinaryStr.substring(0, 3)).append(" ")
					.append(secondBinaryStr.substring(3));
			
			if (twoBinaryStr.startsWith("11")) {
				sb.append("    ").append(
						"F4(日期数据): " + getDay(twoBinaryStr.substring(2)));
			}
			if (twoBinaryStr.startsWith("10")) {
				sb.append("    ").append(
						"F3(时间节点): " + getTime(twoBinaryStr.substring(2)));
			}
			if (twoBinaryStr.startsWith("0") && twoBinaryStr.endsWith("111111111")) {
				sb.append("    ").append(
						"F2(睡眠数据): " + getSleepData(twoBinaryStr.substring(1, 7)));
			}
			if (twoBinaryStr.startsWith("0") && !twoBinaryStr.endsWith("111111111")) {
				sb.append("    ").append(
						"F1(运动数据): " + getSportData(twoBinaryStr.substring(1)));
			}
			sb.append("\n");
		}
		sb.append("总climpNum : " + climpSum + ", 总stepNum : " + stepSum);
		System.out.println(sb.toString());
	}
	
	private static String getBinaryStrFromHexStr(String firstByte) {
		String bs = Integer.toBinaryString(Integer.parseInt(firstByte, 16));
		if(bs.length() < 8){
			int bsLen = 8-bs.length();
			for(int j=0; j<bsLen; j++){
				bs = "0" + bs;
			}
		}
		return bs;
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
		
		if(climpNum < 0 || climpNum > 63){
			System.out.println("爬升高度超出范围: " + climpNum);
		}
		if(stepNum < 0 || stepNum > 350){
			System.out.println("步数度超出范围: " + stepNum);
		}
		
		Test2.climpSum += climpNum;
		Test2.stepSum += stepNum;
		
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
