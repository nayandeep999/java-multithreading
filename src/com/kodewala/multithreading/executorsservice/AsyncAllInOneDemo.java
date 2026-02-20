package com.kodewala.multithreading.executorsservice;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncAllInOneDemo {

	static AtomicReference<String> cache = new AtomicReference<>();

	static String fetchFromDb() {
		sleep(1500);
		System.out.println("DB fetched on " + Thread.currentThread().getName());
		return "USER_123";
	}

	static String callApi(String user) {
		sleep(1000);
		System.out.println("API called for " + user + " on " + Thread.currentThread().getName());
		return user + "_OK";
	}

	public static void main(String[] args) {

		System.out.println("Main starts on " + Thread.currentThread().getName());

		// 1️ Store without blocking (side-effect)
		CompletableFuture<String> dbFuture = CompletableFuture.supplyAsync(AsyncAllInOneDemo::fetchFromDb);

		dbFuture.thenAccept(result -> {
			cache.set(result); // store when ready
			System.out.println("Stored in cache = " + result);
		});

		// 2️ Check condition before proceeding (async if/else)
		dbFuture.thenCompose(user -> {
			if (user.startsWith("USER")) {
				return CompletableFuture.supplyAsync(() -> callApi(user));
			} else {
				return CompletableFuture.failedFuture(new RuntimeException("Invalid user"));
			}
		}).thenAccept(apiResult -> {
			System.out.println("Final result after check = " + apiResult);
		}).exceptionally(ex -> {
			System.err.println("Flow failed: " + ex.getMessage());
			return null;
		});

		// 3️ Timing unpredictability (parallel task)
		CompletableFuture.runAsync(() -> {
			sleep(500);
			System.out.println("Fast task done");
		});

		CompletableFuture.runAsync(() -> {
			sleep(2000);
			System.out.println("Slow task done");
		});

		System.out.println("Main thread keeps running...");

		// ⛔ Only to keep JVM alive for demo
		sleep(3000);

		// This may or may not be set earlier — never rely on timing
		System.out.println("Later read from cache (side-effect only): " + cache.get());
	}

	static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
