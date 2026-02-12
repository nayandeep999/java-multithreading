package com.kodewala.multithreading.mainconcepts;

class Taskz implements Runnable {
	int count;

	@Override
	public synchronized void run() {
		for (int i = 0; i < 100; i++) {
			count++;
			System.out.println(count + Thread.currentThread().getName());
		}
	}
}

public class FixRaceCondition {

	public static void main(String[] args) {

		Taskz task = new Taskz();

		Thread t1 = new Thread(task);
		Thread t2 = new Thread(task);

		t1.start();
		t2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Final expected count value: 200");
		System.out.println("Final actual count value: " + task.count);

	}

}
