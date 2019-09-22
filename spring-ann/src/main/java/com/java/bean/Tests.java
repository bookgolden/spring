package com.java.bean;

import java.util.HashMap;

public class Tests {
	
	public static void main(String[] args) {
		
		HashMap<String, String> map = new HashMap<>();
		
		map.put("123", "qq");
		map.put("123", "ww");
		System.out.println(map.toString());
	}

}
