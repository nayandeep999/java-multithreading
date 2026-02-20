package com.kodewala.multithreading.prodcons;

class Task {
	int data;
	boolean hasData = false;

	public synchronized void produce(int value) {
		while (hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		data = value;
		hasData = true;
		notify();
	}

	public synchronized void consume() {
		while (!hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Consumed: " + data);
		hasData = false;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notify();
	}
}

class Producer extends Thread {

	Task task;

	Producer(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			task.produce(i);
			System.out.println("Produced: " + i);
		}
	}
}

class Consumer extends Thread {

	Task task;

	Consumer(Task task) {
		this.task = task;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			task.consume();
		}
	}

}

public class ProducerAndConsumer {

	public static void main(String[] args) {
		Task task = new Task();
		Producer producer = new Producer(task);
		Consumer consumer = new Consumer(task);

		producer.start();
		consumer.start();
	}

}
