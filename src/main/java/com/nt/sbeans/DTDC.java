//DTDC.java (depedent class2)
package com.nt.sbeans;

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
