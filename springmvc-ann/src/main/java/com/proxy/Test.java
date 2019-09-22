package com.proxy;

public class Test {

	public static void main(String[] args) {
		StaticProxy sp = new StaticProxy(new BaoMa());
//		StaticProxy sp2 = new StaticProxy(new BenChi());
		sp.drive();
//		sp2.drive();
	}

}
