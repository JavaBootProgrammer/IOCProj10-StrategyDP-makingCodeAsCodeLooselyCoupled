# IOCProj10-StrategyDP-makingCodeAsCodeLooselyCoupled


# Code
```Java
//ICourier.java (Common Interface)
package com.courier.sbeans;

public interface ICourier {
   public  String deliver(int oid);
}
//StrategyDPTest.java (Client App)
package com.courier.client;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.courier.config.AppConfig;
import com.courier.sbeans.Flipkart;

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
//AppConfig.java (Confguration class)
package com.courier.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "com.nt.sbeans")
@ImportResource("com/courier/cfgs/applicationContext.xml")
public class AppConfig {

}
//BlueDart.java (Dependency class1)
package com.courier.sbeans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("bDart")
@Lazy(true)
public final class BlueDart implements ICourier {

    public BlueDart() {
        System.out.println("BlueDart:: 0-param constructor");
    }

    @Override
    public String deliver(int oid) {

        return "BlueDart courier is ready to deliver "+oid+" order number  products ";

    }

}
//DHL.java (depedent class3)
package com.courier.sbeans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("dhl")
@Lazy(true)
public final class DHLjava implements ICourier {

    public DHLjava() {
        System.out.println("DHL:: 0-param constructor");
    }

    @Override
    public String deliver(int oid) {

        return "DHL courier is ready to deliver "+oid+" order number  products ";

    }

}
//DTDC.java (depedent class2)
package com.courier.sbeans;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("dtdc")
@Lazy(true)
@Primary
public final class DTDC implements ICourier {

    public DTDC() {
        System.out.println("DTDC:: 0-param constructor");
    }

    @Override
    public String deliver(int oid) {

        return "DTDC courier is ready to deliver "+oid+" order number  products ";

    }

}
//Flipkart.java (target class)
package com.courier.sbeans;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("fpkt")
public final class Flipkart {
    //HAS- property

    //@Qualifier("bDart")
    //@Qualifier("${courier.id}")  ---->place holder ${<key>} should always be used along with  @Value annotation
    //@Qualifier("@Value('${courier.id}')")  ---> we can not place @Value annotation inside another annotation
	/*@Value("${courier.id}")
	private  String id;
	@Qualifier(id)--> Does not allow to pass variables to @Qualifier(-) annotation*/
    @Autowired
    @Qualifier("kart")
    private ICourier courier;

    public Flipkart() {
        System.out.println("Flipkart:: 0-param constructor");
    }



    //b.method
    public  String shopping(String  items[], double prices[] ) {
        System.out.println("Flipkart.shopping()");
        //calculate bill amount
        double billAmt=0.0;
        for(double p:prices) {
            billAmt=billAmt+p;
        }
        //generate order id randomly
        int oid=new Random().nextInt(100000);
        // deliver the order using couier
        String msg=courier.deliver(oid);
        return Arrays.toString(items)+"are shopped having bill amount::"+billAmt+" --->"+msg;
    }


}
```

# Properties
```properties
# specify the depedent spring bean id here
courier.id=dhl
```

# Configuration
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xmlns:context="http://www.springframework.org/schema/context"
   xsi:schemaLocation="http://www.springframework.org/schema/beans   http://www.springframework.org/schema/beans/spring-beans.xsd
                                             http://www.springframework.org/schema/context   http://www.springframework.org/schema/context/spring-context.xsd">
     <!-- Configure  the properties file here -->
     <context:property-placeholder location="com/nt/commons/Info.properties"/>
     
     <!-- provide  fixed alias name  for the bean id kept colleted from the properties file -->
     <alias name="${courier.id}" alias="kart"/>
   </beans>



```

# UML
![UML](src/main/resources/UML.png)