package com.sqh.test.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	private static String DEFAULT_ENCODE = "UTF-8";
	public static List<String> getContent(String encode, String filePath){
		List<String> strs = new ArrayList<String>();
		if(encode == null){
			encode = DEFAULT_ENCODE;
		}
		
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), encode));
			String temp = null;
			while((temp = br.readLine()) != null){
				if(temp.trim().length() > 0){
					strs.add(temp.trim());
				}
			}
			return strs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}
}	
