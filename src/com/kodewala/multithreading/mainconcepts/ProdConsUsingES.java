package com.kodewala.multithreading.mainconcepts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Tasky {

	boolean hasData;
	List<Integer> list = new ArrayList<Integer>();

	public synchronized void produce(int num) throws InterruptedException {
		while (hasData) {
			wait();
		}
		list.add(num);
		System.out.println(num + " produced by thread: " + Thread.currentThread().getName());
		hasData = true;
		notify();
	}

	public synchronized void consume() throws InterruptedException {
		while (!hasData) {
			wait();
		}
		int consumed = list.remove(0);
		System.out.println(consumed + " consumed by thread: " + Thread.currentThread().getName());
		hasData = false;
		notify();
	}

}

class Producerx implements Runnable {

	Tasky task;

	Producerx(Tasky task) {
		this.task = task;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				task.produce(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

class Consumerx implements Runnable {

	Tasky task;

	Consumerx(Tasky task) {
		this.task = task;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				task.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

public class ProdConsUsingES {

	public static void main(String[] args) {

		Tasky task = new Tasky();

		ExecutorService producer = Executors.newSingleThreadExecutor();
		producer.submit(new Producerx(task));

		ExecutorService consumer = Executors.newSingleThreadExecutor();
		consumer.submit(new Consumerx(task));

		producer.shutdown();
		consumer.shutdown();

		// Even if you create multiple producer and consumer threads
		// only 1 thread will run at a time due to synchronized
		// if a producer thread gets a chance it will block all the other producer &
		// consumer threads
		// similarly if a consumer thread gets a chance it will block all the other
		// producer &
		// consumer threads

	}

}
