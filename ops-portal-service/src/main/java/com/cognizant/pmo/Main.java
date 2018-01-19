/**
 * 
 */
package com.cognizant.pmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author lenovo
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cognizant.pmo")
@EntityScan({ "com.cognizant.pmo.entity" })
@EnableJpaRepositories(basePackages = {"com.cognizant.pmo.repository",
		"com.cognizant.pmo.entity"
})
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
