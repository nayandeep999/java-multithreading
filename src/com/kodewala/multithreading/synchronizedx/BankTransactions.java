package com.kodewala.multithreading.synchronizedx;

class Account {
	int balance = 2000;

	public synchronized void credit(int amount, int noOfTransactions) {
		for (int i = 0; i < noOfTransactions; i++) {
			System.out.println("Loop: " + i + " Crediting 100 by: " + Thread.currentThread().getName());
			this.balance = this.balance + amount;
		}
		System.out.println("For loop ends");
		System.out.println("Final balance after credit: " + balance);
	}

	public synchronized void debit(int amount, int noOfTransactions) {
		for (int i = 0; i < noOfTransactions; i++) {
			System.out.println("Loop: " + i + " Debiting 100 by: " + Thread.currentThread().getName());
			this.balance = this.balance - amount;
		}
		System.out.println("For loop ends");
		System.out.println("Final balance after debit: " + balance);
	}
}

class Worker extends Thread {

	Account acc;
	boolean creditFlag;

	public Worker(Account acc, boolean creditFlag) {
		this.acc = acc;
		this.creditFlag = creditFlag;
	}

	@Override
	public void run() {
		if (creditFlag) {
			acc.credit(100, 20);
		} else
			acc.debit(100, 10);
	}
}

public class BankTransactions {

	public static void main(String[] args) {
		Account sharedAcc = new Account();
		Worker credit = new Worker(sharedAcc, true);
		credit.setName("CREDIT-THREAD");

		Worker debit = new Worker(sharedAcc, false);
		debit.setName("DEBIT-THREAD");

		credit.start();
		debit.start();
	}

}
