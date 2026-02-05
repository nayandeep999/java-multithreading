package com.kodewala.multithreading;

public class MainThread {

	public static void main(String[] args) {
		System.out.println("main() method is executed by thread: " + Thread.currentThread().getName());
		MainThread mt = new MainThread();
		mt.doSomething();
	}

	public void doSomething() {
		System.out.println("doSomething() is executed by thread: " + Thread.currentThread().getName());
	}

}
