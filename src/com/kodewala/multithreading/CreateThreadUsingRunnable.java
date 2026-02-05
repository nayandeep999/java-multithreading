package com.kodewala.multithreading;

class CustomThread implements Runnable { // its better than extending Thread class
	// because now CustomThread can extend another class
	@Override
	public void run() {
		System.out.println("Hello from the thread : " + Thread.currentThread().getName());
	}
}

public class CreateThreadUsingRunnable {

	public static void main(String[] args) {
		CustomThread ct = new CustomThread();
		Thread t1 = new Thread(ct); // New state
		t1.start(); // Runnable

		/*-
		 * New state -> Runnable -> Running -> Blocked/Waiting -> Runnable -> Running (gets CPU time) -> Terminated (Dead)
		 * A thread can go from Runnable -> Wait , when it tries to acquire a lock
		 * which is already held by some other thread
		 * */

		// t1.start(); -> You cannot restart thread again -> Exception in thread "main"
		// java.lang.IllegalThreadStateException
	}

}
