package com.kodewala.multithreading.prodcons;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureEx {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
			// ForkJoinPool.commonPool-worker-1
			System.out.println("Task1: Executed by " + Thread.currentThread().getName());
			sleep(1500);
			return 20;
		});

		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Task2: Executed by " + Thread.currentThread().getName());
			// business logic ....+ in the end we return some result
			sleep(1500);
			return 40;
		});

		CompletableFuture<Integer> combineTask = task1.thenCombineAsync(task2, (a, b) -> { // this method creates new
																							// thread
			return a + b;
		});

		CompletableFuture<Integer> appliedTask = task1.thenApplyAsync((a) -> {
			System.out.println("Task1: then apply async " + Thread.currentThread().getName());
			return a * 5;
		});

		CompletableFuture<Integer> appliedTaskNonAsync = task1.thenApply((a) -> {
			System.out.println("Task1: then apply non async " + Thread.currentThread().getName());
			return a * 5;
		});

		System.out.println("Result is :" + combineTask.join());

		// different thread, creates a async thread - ForkJoinPool.commonPool-worker-2
		System.out.println("Applied async task " + appliedTask.join());

		// same thread as supplyAsync - ForkJoinPool.commonPool-worker-1
		System.out.println("Applied non async task " + appliedTaskNonAsync.join());
		long end = System.currentTimeMillis();
		System.out.println("total time taken " + (end - start));
	}

	public static void sleep(int _time) {
		try {
			Thread.sleep(_time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
