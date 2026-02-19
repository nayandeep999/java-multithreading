package com.kodewala.multithreading;

class Demo extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("printing " + i + " executed by thread: " + Thread.currentThread().getName());
		}
	}
}

public class UserCreatedThread {
	public static void main(String[] args) {
		System.out.println("START: main() method is executed by thread: " + Thread.currentThread().getName());
		Demo d1 = new Demo();
		d1.start();

		Demo d2 = new Demo();
		d2.start(); // this will create a new thread (register a thread in thread scheduler)
		System.out.println("END: main() method is executed by thread: " + Thread.currentThread().getName());

		// d2.start(); // Exception in thread "main"
		// java.lang.IllegalThreadStateException

		/// We cannot predict the execution order because thread execution is controlled
		/// by the
		/// thread scheduler and CPU availability.
		/// The scheduler may give different CPU time slices to each thread,
		/// so their execution order and timing are non-deterministic.

		/// THREAD-1 RUNS for 2 seconds, at the same time THREAD-2 runs for only 1
		/// seconds

		/*-START: main() method is executed by thread: main
		END: main() method is executed by thread: main
		printing 0 executed by thread: Thread-1
		printing 1 executed by thread: Thread-1
		printing 2 executed by thread: Thread-1
		printing 3 executed by thread: Thread-1
		printing 0 executed by thread: Thread-0
		printing 4 executed by thread: Thread-1
		printing 1 executed by thread: Thread-0
		printing 2 executed by thread: Thread-0
		printing 3 executed by thread: Thread-0
		printing 4 executed by thread: Thread-0
		*/
	}

}
