package com.kodewala.multithreading.sealedclasses;

sealed class Bank permits HDFC {

}

sealed class HDFC extends Bank permits HDFCPaymentGateway {

}

final class HDFCPaymentGateway extends HDFC {

}

//class Axis extends Bank{
//	
//}

public class SealedClasses {

	public static void main(String[] args) {
		Bank hdfc = new HDFC();
		HDFC hdfcPaymentGateway = new HDFCPaymentGateway();
	}

}
