package com.proxy;

public class StaticProxy implements Car{

	private Car car;

	public StaticProxy(Car car) {
		this.car = car;
	}

	public void drive() {
		System.out.println("static ...");
		car.drive();
		System.out.println("static ...");
	}
	
	public static void main(String[] args) {
		Car car = new StaticProxy(new BenChi());
		car.drive();
	}
	
}
