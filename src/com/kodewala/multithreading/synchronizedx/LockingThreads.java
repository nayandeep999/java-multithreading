package com.kodewala.multithreading.synchronizedx;

class PrintNumbers {
	public synchronized void print() { // Thread must acquire lock for this object
		for (int i = 0; i < 10; i++) {
			System.out.println(i + " executed by: " + Thread.currentThread().getName());
		}

	}
}

class Worker extends Thread {
	PrintNumbers pn;

	Worker(PrintNumbers pn) {
		this.pn = pn;
	}

	@Override
	public void run() {
		pn.print();
	}
}

public class LockingThreads {

	public static void main(String[] args) {

		// Every object have only one lock
		// Thread must acquire lock to execute the synchronized block
		// Whichever thread acquire lock first will execute the sync part first
		// And the other thread will wait for the first thread to finish
		// Once the first thread finishes it releases the lock back to the object
		// Now the other thread can acquire this lock and execute the sync part

		PrintNumbers pn = new PrintNumbers();
		Worker t1 = new Worker(pn);

		t1.setName("T1");
		t1.start();

		Worker t2 = new Worker(pn);
		t2.setName("T2");
		t2.start();

		System.out.println("main() finished...");

	}

}
