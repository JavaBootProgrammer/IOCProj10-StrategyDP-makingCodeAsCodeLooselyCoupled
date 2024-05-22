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
