package dependency;

public class SMSNotification implements CustomerNotificationService {
	public SMSNotification() {
		System.out.println("in ctor of " + getClass());
	}

	@Override
	public void alertCustomer(String mesg) {
		System.out.println("Notifying the customer using " 
	+ getClass() + " : mesg " + mesg);

	}

}
