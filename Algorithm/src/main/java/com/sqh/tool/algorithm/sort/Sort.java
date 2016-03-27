package com.sqh.tool.algorithm.sort;

import java.util.List;

/**
 * 排序接口
 * @author suqihai
 * @param <T>
 */
public interface Sort<T> {
	
	/**
	 * 排序
	 * @param datas
	 * @return
	 */
	public List<T> sort(List<T> datas);
}
