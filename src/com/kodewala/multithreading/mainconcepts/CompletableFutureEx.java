package com.kodewala.multithreading.mainconcepts;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureEx {

	public static void main(String[] args) {

		CompletableFuture<String> helloWorldCF = CompletableFuture.supplyAsync(() -> "Hello World");
		CompletableFuture<String> newWorldCF = CompletableFuture.supplyAsync(() -> "New World");

		CompletableFuture<String> combineBoth = helloWorldCF.thenCombineAsync(newWorldCF, (a, b) -> a + b);
		combineBoth.thenAccept((s) -> System.out.println(s));
	}

}
