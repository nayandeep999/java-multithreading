package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

class Taskey {

	private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();

	public void produce(int num) throws InterruptedException {
		queue.put(num);
		System.out.println(num + " produced by thread: " + Thread.currentThread().getName());
	}

	public void consume() throws InterruptedException {
		int consumed = queue.take();
		System.out.println(consumed + " consumed by thread: " + Thread.currentThread().getName());
	}

}

class Producerxx implements Runnable {

	Taskey task;

	Producerxx(Taskey task) {
		this.task = task;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				task.produce(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

class Consumerxx implements Runnable {

	Taskey task;

	Consumerxx(Taskey task) {
		this.task = task;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				task.consume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

// we will achieve multiple producer & consumer threads
// running at the same time - parallely
public class AdvancedProdCons {

	public static void main(String[] args) {

		Taskey task = new Taskey();

		ExecutorService producerPool = Executors.newFixedThreadPool(5);
		// Submit 5 separate producer tasks
		for (int i = 0; i < 5; i++) {
			producerPool.submit(new Producerxx(task));
		}

		ExecutorService consumerPool = Executors.newFixedThreadPool(5);
		// Submit 5 separate consumer tasks
		for (int i = 0; i < 5; i++) {
			consumerPool.submit(new Consumerxx(task));
		}

		producerPool.shutdown();
		consumerPool.shutdown();

	}

}
