package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATMImpl;

public class TestSpringContainer {

	public static void main(String[] args) {
		// BeanFactory <----- AppCtx <-----ClassPathXmlAppCtx
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("my-config.xml")) {
			System.out.println("Spring up n running....");
			/*
			 * Make 3 demands (getBean) to get ATM bean/s 
			 * Invoke B.L (deposit : 1000)
			 */
			//Tester is demanding to SC : to ret a rdy to use bean(cls loading---> inst-->
			//setter based D.I --> init : rdy to use bean
			ATMImpl bean1=ctx.getBean("atm", ATMImpl.class);
			//invoke B.L
			bean1.deposit(1000);
			//next demand
			ATMImpl bean2=ctx.getBean("atm", ATMImpl.class);
			//next demand
			ATMImpl bean3=ctx.getBean("atm", ATMImpl.class);
			System.out.println(bean1 == bean2);//f
			System.out.println(bean1 == bean3);//f
		} //ctx.close-> SC shut down
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
