package com.kodewala.multithreading.synchronizedx;

class PrintNum {
	public synchronized void print() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(i + " printed by: " + Thread.currentThread().getName());
		}
	}
}

class Workers extends Thread {
	PrintNum printNum;

	Workers(PrintNum pn) {
		this.printNum = pn;
	}

	@Override
	public void run() {
		printNum.print();
	}
}

public class SynchronizedWorksWithSameObj {

	public static void main(String[] args) {
		PrintNum pn1 = new PrintNum();
		PrintNum pn2 = new PrintNum();

		Workers w1 = new Workers(pn1);
		w1.setName("Worker-1");
		w1.start();

		Workers w2 = new Workers(pn2);
		w2.setName("Worker-2");
		w2.start();

		// Now threads will not be synchronized because they are working on two
		// different objects so they will just run paralleley

	}

}
