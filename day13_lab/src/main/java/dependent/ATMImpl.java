package dependent;

import dependency.CustomerNotificationService;
import dependency.Transport;

public class ATMImpl implements ATM {
	private Transport myTransport;
	private double cash;
	private CustomerNotificationService service;

	public ATMImpl(double cash123) {
		cash = cash123;
		System.out.println("in cnstr of " + getClass().getName() + " " + cash + " " + myTransport + " " + service);// null
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		// send alert to customer
		service.alertCustomer("You have deposited " + amt);
		myTransport.informBank(data);// depnt using depcy to inform underlying bank

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		// send alert to customer
		service.alertCustomer("You have withdrawn " + amt);
		myTransport.informBank(data);// depnt using depcy to inform underlying bank
	}

	// setter based D.I
	public void setMyTransport(Transport myTransport) {
		this.myTransport = myTransport;
		System.out.println("in setter " + myTransport);// not null
	}
	//setter Based D.I
	public void setService(CustomerNotificationService service) {
		this.service = service;
		System.out.println("in setter " + myTransport+" "+service);// not null
	}

	// init
	public void init1234() {
		System.out.println("in init " + myTransport);// not null
	}

	

	// destroy
	public void destroy1234() {
		System.out.println("in destroy " + myTransport);// not null
	}

}
