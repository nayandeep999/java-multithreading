package com.kodewala.multithreading.executorsservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

class Print implements Supplier<List<Integer>> {
	@Override
	public List<Integer> get() {
		List<Integer> list = new ArrayList();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		return list;
	}
}

public class CompletableFutureEx {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("task1 - thread: " + Thread.currentThread().getName() + " waiting for 5 seconds");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 20;
		});

		System.out.println(task1.join()); // main thread waits here for 5 seconds

		CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("task2 - thread: " + Thread.currentThread().getName() + " waiting for 5 seconds");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return 20;
		});

		System.out.println(task2.join()); // gain main thread waits here for 5 seconds

		long endTime = System.currentTimeMillis();

		System.out.println("Total time taken in seconds: " + (endTime - startTime) / 1000);

		CompletableFuture<Integer> task3 = task1.thenCombineAsync(task2, (a, b) -> a + b);
		System.out.println("task1 + task2 = " + task1.join() + " + " + task2.join() + " = " + task3.join());

		Integer res1 = task1.thenApply((a) -> a * 10).join();
		System.out.println(res1);

		Integer res2 = task2.thenApplyAsync((b) -> b * 10).join();
		System.out.println(res2);

		CompletableFuture<Void> task4 = CompletableFuture.runAsync(() -> {
			System.out.println("just a void method, doesnot return anything");
		});

		System.out.println("task4 will return null: " + task4.join());

		CompletableFuture<List<Integer>> task5 = CompletableFuture.supplyAsync(new Print());
		System.out.println("The list is: " + task5.join());

	}

}
