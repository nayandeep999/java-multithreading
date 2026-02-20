package com.kodewala.multithreading.prodcons;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Tasks {
	ReentrantLock rt = new ReentrantLock();

	public void print() throws InterruptedException {
		System.out.println("Line 1 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 2 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 3 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 4 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 5 executed by: " + Thread.currentThread().getName());
		System.out.println("this line is not synchronized....." + Thread.currentThread().getName());
		// rt.lock();
		rt.tryLock(500, TimeUnit.MILLISECONDS);
		for (int i = 0; i < 10; i++) {
			System.out.println("Synchronized " + i + " printed by: " + Thread.currentThread().getName());
		}
		rt.unlock();

		System.out.println("Line 6 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 7 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 8 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 9 executed by: " + Thread.currentThread().getName());
		System.out.println("Line 10 executed by: " + Thread.currentThread().getName());
		System.out.println("again this line is not synchronized....." + Thread.currentThread().getName());

	}
}

class Worker extends Thread {
	Tasks task;

	Worker(Tasks task) {
		this.task = task;
	}

	public void run() {
		try {
			task.print();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class ReentrantLockEx {

	public static void main(String[] args) {
		Tasks task = new Tasks();
		Worker t1 = new Worker(task);
		Worker t2 = new Worker(task);

		t1.start();
		t2.start();
	}

}
