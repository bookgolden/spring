package com.java;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortNums {
	public static void main(String[] args) {
		String str = "acb125vf13679dD451366666666666cc66666666666666789ABCDEF";
//		String str = "abcA125ldsdfjs67891345679sfdsdf45136789JJJ";
//		String str = "acb125vf13679";
//		String str = "acb125vf13679dD4513678ABCDEF";
		
		List<String> list = Arrays.asList(str.split("[a-zA-Z]{1,}"));

		List<String> resultList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			resultList.addAll(getItem(list.get(i)));
		}
		System.out.println(getMax(resultList));
	}

	public static List<String> getItem(String str) {
		if ("".equals(str.trim())) {
			return new ArrayList<String>();
		}
		List<String> midList = new ArrayList<String>();
		if (str.length() < 2) {
			midList.add(str);
			return midList;
		}

		String[] innerArray = str.split("");
		String tempStr = innerArray[0];

		for (int j = 1; j < innerArray.length; j++) {
			if (Integer.parseInt(innerArray[j - 1]) > Integer.parseInt(innerArray[j])) {
				midList.add(tempStr);
				tempStr = "";
			}
			tempStr += innerArray[j];
		}
		midList.add(tempStr);
		return midList;
	}

	public static String getMax(List<String> list) {
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return new BigInteger(o2).compareTo(new BigInteger(o1));
			}
		});
		return list.get(0);
	}
}