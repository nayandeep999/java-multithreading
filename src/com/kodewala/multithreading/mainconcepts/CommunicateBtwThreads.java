package com.kodewala.multithreading.mainconcepts;

import java.util.ArrayList;
import java.util.List;

class Taski {
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
		while (hasData) {
			int consumed = list.remove(0);
			System.out.println(consumed + " consumed by thread: " + Thread.currentThread().getName());
			hasData = false;
			notify();
		}
		wait();
	}
}

class Producer extends Thread {
	Taski task;

	public Producer(Taski task) {
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

class Consumer extends Thread {
	Taski task;

	public Consumer(Taski task) {
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

public class CommunicateBtwThreads {

	public static void main(String[] args) {

		Taski task = new Taski();

		Producer p = new Producer(task);
		p.setName("PRODUCER");

		Consumer c = new Consumer(task);
		c.setName("CONSUMER");

		p.start();
		c.start();

	}

}
