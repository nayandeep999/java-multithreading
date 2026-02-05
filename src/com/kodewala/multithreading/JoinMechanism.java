package com.kodewala.multithreading;

class Cook implements Runnable {
	public void run() {
		System.out.println("Let the bro cook..");
		System.out.println("Cooking...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Cooked.");
	}
}

public class JoinMechanism {

	public static void main(String[] args) {
		Cook c = new Cook();
		Thread t1 = new Thread(c);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Now serving cooked food");
	}

}
