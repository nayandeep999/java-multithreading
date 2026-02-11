package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class ExtendsThreadClass extends Thread {

	public ExtendsThreadClass(Runnable runnable, String string) {
		super(runnable, string);
	}
}

class ImplemetsRunnable implements Runnable {
	@Override
	public void run() {
		System.out.println("Second way to craete thread: " + Thread.currentThread().getName());
	}
}

class ImplementsCallable implements Callable<String> {
	@Override
	public String call() {
		return "Returing a string";
	}
}

public class FiveWaysToCreateThreads {

	public static void main(String[] args) {

		// First way - directly using the thread class
		ExtendsThreadClass t1 = new ExtendsThreadClass(
				() -> System.out.println("First way to create thread: " + Thread.currentThread().getName()),
				"First-Way-Thread");
		t1.start();

		// Second way - implement Runnable
		Thread t2 = new Thread(new ImplemetsRunnable(), "Second-Way-Thread");
		t2.start();

		// Third way - using Callable & Future Task
		FutureTask<String> future = new FutureTask(new ImplementsCallable());
		Thread t3 = new Thread(future, "Third-Way-Thread");
		t3.start();
		try {
			String str = "Third way " + future.get() + " from Callable & FutureTask";
			System.out.println(str);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Fourth way - using Executors Service
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Future<String> futureObj = ex.submit(new ImplementsCallable());
		try {
			String str1 = "Fourth way " + futureObj.get() + " from executor service";
			System.out.println(str1);
			ex.shutdown();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		// Fifth way - using Completable Future
		CompletableFuture<String> cf = CompletableFuture
				.supplyAsync(() -> "Fifth way Returns a string from completable future");
		cf.thenAccept(s -> System.out.println(s));

	}

}
