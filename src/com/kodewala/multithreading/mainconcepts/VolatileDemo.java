package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {

	private volatile boolean running = true;

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			System.out.println(Thread.currentThread().getName() + " keeps running");
		}
		System.out.println(Thread.currentThread().getName() + " stopping gracefully.");
	}
}

public class VolatileDemo {

	public static void main(String[] args) throws InterruptedException {

		MyTask task = new MyTask();

		ExecutorService ex = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			ex.submit(task);
		}

		task.stop();
		ex.shutdown();
		Thread.sleep(1000);
		System.out.println("main() execution finished.");

	}

}
