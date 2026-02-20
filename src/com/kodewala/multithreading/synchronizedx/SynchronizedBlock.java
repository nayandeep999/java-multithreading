package com.kodewala.multithreading.synchronizedx;

class Task {
	public void print() {
		// multiple threads can execute this until sync blocked
		System.out.println("Printing line 1 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 2 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 3 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 4 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 5 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 6 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 7 by thread: " + Thread.currentThread().getName());

		// single threaded one thread at a time
		synchronized (this) {
			System.out.println("Synchronized: Printing line 1 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 2 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 3 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 4 by thread: " + Thread.currentThread().getName());
		}
	}

	public static void staticPrint() {
		// multiple threads can execute this until sync blocked
		System.out.println("Printing line 1 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 2 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 3 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 4 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 5 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 6 by thread: " + Thread.currentThread().getName());
		System.out.println("Printing line 7 by thread: " + Thread.currentThread().getName());

		// single threaded one thread at a time
		synchronized (Task.class) {
			System.out.println("Synchronized: Printing line 1 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 2 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 3 by thread: " + Thread.currentThread().getName());
			System.out.println("Synchronized: Printing line 4 by thread: " + Thread.currentThread().getName());
		}

	}
}

class WorkerThread extends Thread {
	Task task;

	WorkerThread(Task task) {
		this.task = task;
	}

	public void run() {
		Task.staticPrint();
	}
}

public class SynchronizedBlock {

	public static void main(String[] args) {
		Task sharedTask = new Task();
		WorkerThread t1 = new WorkerThread(sharedTask);
		t1.setName("Thread-1");
		WorkerThread t2 = new WorkerThread(sharedTask);
		t2.setName("Thread-2");

		t1.start();
		t2.start();
	}

}
