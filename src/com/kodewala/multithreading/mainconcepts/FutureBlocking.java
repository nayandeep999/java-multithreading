package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureBlocking {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		ExecutorService ex = Executors.newFixedThreadPool(5);
		Integer result = 0;
		for (int i = 0; i < 5; i++) {
			Future<Integer> future = ex.submit(() -> {
				Thread.sleep(1000);
				return 10;
			});
			try {
				result += future.get(); // this is blocking
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		ex.shutdown();
		long endTime = System.currentTimeMillis();
		System.out.println("Total time taken: " + (endTime - startTime) / 1000 + " seconds");
		// taking 5 seconds because main threads waits 1 seconds for each task then move
		// forward to next loop

	}

}
