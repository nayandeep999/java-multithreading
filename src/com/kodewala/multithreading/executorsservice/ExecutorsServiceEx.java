package com.kodewala.multithreading.executorsservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Taskz implements Callable<String> {

	int values;

	Taskz(int i) {
		this.values = i;
	}

	@Override
	public String call() throws InterruptedException {
		String str = "hello";
		str.toUpperCase();
		System.out.println(values + " Its is executed by: " + Thread.currentThread().getName());
		Thread.sleep(2000);
		return str;
	}

}

public class ExecutorsServiceEx {

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			Future<String> future = ex.submit(new Taskz(i)); // this will return a Future object (this is not blocking)
			try {
				System.out.println("The return is :" + future.get()); // here future.get() is blocking -> current thread
																		// waits for the response and block other
																		// threads
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		ex.shutdown(); // must shutdown
	}

}
