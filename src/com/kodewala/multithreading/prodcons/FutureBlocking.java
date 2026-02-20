package com.kodewala.multithreading.prodcons;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Taskzz implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("call() - " + Thread.currentThread().getName());
		Thread.sleep(5000);
		return 1;
	}
}

public class FutureBlocking {

	public static void main(String[] args) {
		Taskzz t1 = new Taskzz();
		Integer res = 0;
		Long startTime = System.currentTimeMillis();
		ExecutorService ex = Executors.newFixedThreadPool(12);
		for (int i = 0; i < 2; i++) {
			Future<Integer> f = ex.submit(t1);
			try {
				res = res + f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		Long finishTime = System.currentTimeMillis();
		Long resTime = (finishTime - startTime) / 1000;
		System.out.println(resTime);
		ex.shutdown();
	}

}
