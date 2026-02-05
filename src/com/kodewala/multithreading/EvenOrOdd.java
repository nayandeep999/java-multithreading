package com.kodewala.multithreading;

class CheckEven extends Thread {

	int num;

	CheckEven(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println("Thread running check even: " + Thread.currentThread().getName());
		for (int i = 0; i < num; i++) {
			if (i % 2 == 0) {
				System.out.println(i + " is even" + " - executed by: " + Thread.currentThread().getName());
			} else
				System.out.println(i + " is not even" + " - executed by: " + Thread.currentThread().getName());
		}
	}

}

class CheckOdd extends Thread {

	int num;

	CheckOdd(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println("Thread running check odd: " + Thread.currentThread().getName());
		for (int i = 0; i < num; i++) {
			if (i % 2 == 1) {
				System.out.println(i + " is odd" + " - executed by: " + Thread.currentThread().getName());
			} else
				System.out.println(i + " is not odd" + " - executed by: " + Thread.currentThread().getName());
		}
	}

}

public class EvenOrOdd {

	public static void main(String[] args) {
		CheckEven ce = new CheckEven(10);
		ce.setName("Even Thread");
		ce.start();

		CheckOdd co = new CheckOdd(12);
		co.setName("Odd Thread");
		co.start();
	}

}
