package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class MyTaskV1 implements Runnable {

	private AtomicInteger count = new AtomicInteger(0);

	public AtomicInteger getCount() {
		return count;
	}

	@Override
	public void run() {
		count.incrementAndGet();
		System.out.println(count + " " + Thread.currentThread().getName());
	}

}

public class AtomicDemo {

	public static void main(String[] args) throws InterruptedException {

		MyTaskV1 task = new MyTaskV1();

		ExecutorService ex = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 100; i++) {
			ex.submit(task);
		}

		Thread.sleep(1000);

		ex.shutdown();
		System.out.println("Expected value: 100");
		System.out.println("Actual value: " + task.getCount());

	}

}
