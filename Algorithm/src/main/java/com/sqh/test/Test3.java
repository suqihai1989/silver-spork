package com.sqh.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {
	public static SimpleDateFormat ALL_FORM = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat DAY_FORM = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat DAY_SIMPLE_FORM = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat ALL_FORM_SPE = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static void main(String[] args){
		System.out.println(DAY_SIMPLE_FORM.format(new Date()));
	}
}
