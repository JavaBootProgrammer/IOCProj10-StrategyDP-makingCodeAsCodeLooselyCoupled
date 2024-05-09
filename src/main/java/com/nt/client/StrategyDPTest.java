//StrategyDPTest.java (Client App)
package com.nt.client;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.nt.config.AppConfig;
import com.nt.sbeans.Flipkart;

public class StrategyDPTest {

	public static void main(String[] args) {
	  //create the IOC container
		  AnnotationConfigApplicationContext ctx=
				    new  AnnotationConfigApplicationContext(AppConfig.class);
		  //get target spring bean class obj ref
		  Flipkart fpkt=ctx.getBean("fpkt",Flipkart.class);
		  //invoke the b.method
		  String resultMsg=fpkt.shopping(new String[] {"shirt","trouser"}, 
				                                                       new double[] {90000.0,50000.0});
		  System.out.println(resultMsg);
         //close the container
		    ctx.close();
	}

}
