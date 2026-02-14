package com.kodewala.multithreading.mainconcepts;

class Tasko implements Runnable {
	@Override
	public void run() {
		System.out.println("1. Before synchronized: " + Thread.currentThread().getName());
		System.out.println("2. Before synchronized: " + Thread.currentThread().getName());
		System.out.println("3. Before synchronized: " + Thread.currentThread().getName());
		System.out.println("4. Before synchronized: " + Thread.currentThread().getName());
		System.out.println("5. Before synchronized: " + Thread.currentThread().getName());
		System.out.println("6. Before synchronized: " + Thread.currentThread().getName());

		synchronized (this) {
			System.out.println("7. This is synchronized: " + Thread.currentThread().getName());
			System.out.println("8. This is synchronized: " + Thread.currentThread().getName());
			System.out.println("9. This is synchronized: " + Thread.currentThread().getName());
			System.out.println("10. This is synchronized: " + Thread.currentThread().getName());
		}

		System.out.println("11. After synchronized: " + Thread.currentThread().getName());
		System.out.println("12. After synchronized: " + Thread.currentThread().getName());
		System.out.println("13. After synchronized: " + Thread.currentThread().getName());
		System.out.println("14. After synchronized: " + Thread.currentThread().getName());
		System.out.println("15. After synchronized: " + Thread.currentThread().getName());

	}
}

public class SyncBlock {

	public static void main(String[] args) {

		Tasko task = new Tasko();

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);

		t1.start();
		t2.start();

	}

}
