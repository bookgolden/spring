package com.java;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;



public class Test  {
	
	public static String str = "acb125vf13679dD451366666666666cc66666666666666789ABCDEF";
//	public static String str = "abcA125ldsdfjs67891345679sfdsdf45136789JJJ";
//	public static String str = "acb125vf13679";
//	public static String str = "acb125vf13679dD4513678ABCDEF";


	public static String getSubMax(String[] x) {
		ArrayList<String> tmp = new ArrayList<String>(); 
		for(int i = 0;i <x.length; i++) {
			ArrayList<String> list = new ArrayList<String>(); 
			for(int j = i + 1;j < x.length; j++) {
				if (list.size() == 0) {
					list.add(x[i]);
				}
				if (x[i].compareTo(x[j]) < 0){
					list.add(x[j]);
				} else{
					break;
				}
			}
			tmp.add(list.toString());
		}
		List<String> xList = tmp.stream().map(y->{
			y = y.replaceAll(" ", "")
				 .replaceAll(",", "")
				 .replaceAll("\\[", "")
				 .replaceAll("\\]", "");
			return y;
		}).filter(string -> !string.isEmpty()).collect(Collectors.toList());
		xList.sort((a,b)->b.length()-a.length());
	    xList.sort((a,b)->new BigDecimal(b).compareTo(new BigDecimal(a)));
	    return xList.get(0);
	}
	
	public static String[] getArry(String s) {
		String[] arr = new String[s.length()];
		for(int i=0;i<s.length();i++) {
			arr[i] = String.valueOf(s.charAt(i));
		}
		return arr;
	}
	
	public static void main (String[] args) {
		String[] arr = str.split("[a-zA-z]");
		List<String> list = new ArrayList<String>();
		for(String c:arr) {
			if (!StringUtils.isEmpty(c)) list.add(c);
//			if (!"".equals(c)) list.add(c);
		}
		List<String> my = new ArrayList<String>();
		for(String c:list) {
			String[] myarr = getArry(String.valueOf(c));
			my.add(getSubMax(myarr)+"");
		}
		my.sort((a,b)->b.length()-a.length());
		my.sort((a,b)->new BigDecimal(b).compareTo(new BigDecimal(a)));
		System.out.println("结果:"+my.get(0));
	}
}