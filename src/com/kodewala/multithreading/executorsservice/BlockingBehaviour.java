package com.kodewala.multithreading.executorsservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ReturnNum implements Callable<Integer> {
	int num = 20;

	public ReturnNum(int num) {
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("ReturnNum.call() method and num is :" + num + " " + Thread.currentThread().getName());
		System.out.println("ReturnNum.call() method sleeping for 5 seconds : " + Thread.currentThread().getName());
		Thread.sleep(5000);
		return num;
	}
}

class ReturnNum1 implements Callable<Integer> {
	int num = 30;

	public ReturnNum1(int num) {
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("ReturnNum1.call() method and num is :" + num + " " + Thread.currentThread().getName());
		System.out.println("ReturnNum1.call() method sleeping for 2 seconds : " + Thread.currentThread().getName());
		Thread.sleep(2000);
		return num;
	}
}

public class BlockingBehaviour {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		long time1 = System.currentTimeMillis();

		ExecutorService service = Executors.newFixedThreadPool(2);

		Future<Integer> future = service.submit(new ReturnNum(20)); // Runs parallel
		Future<Integer> future1 = service.submit(new ReturnNum1(30)); // Runs parallel

		// main is blocked for 5 seconds
		System.out.println("ReturnNum returns: " + future.get() + " " + Thread.currentThread().getName());
		// 2 seconds already over
		System.out.println("ReturnNum1 returns: " + future1.get() + " " + Thread.currentThread().getName());

		service.shutdown();

		long time2 = System.currentTimeMillis();
		long totaltime = (time2 - time1) / 1000;
		System.out.println("Total time taken in seconds: " + totaltime); // Only the max when running parallel

	}

}
