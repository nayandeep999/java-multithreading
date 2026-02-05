package com.kodewala.multithreading;

class Uselessclass implements Runnable {
	public void run() { // Running
		System.out.println("run method is running");
		try {
			System.out.println("Thread is sleeping");
			Thread.sleep(5000); // 5000 ms -> 5 s (TIME WAITING) Blocked / Waiting state
			System.out.println("Sleep time over............"); // this will not execute if thread is interrupted due to
																// inter. exception
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted");
		}
	}
}

public class TimeWaiting {

	public static void main(String[] args) {
		Uselessclass uc = new Uselessclass();
		Thread t1 = new Thread(uc); // New state
		t1.start(); // Runnable
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.interrupt(); // main thread is interrupting t1 thread
	}

}
