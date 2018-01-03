/**
 * 
 */
package com.cognizant.pmo.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lenovo
 *
 */
@RestController
public class SampleController {
	
	@GetMapping("/api/hello")
	public String hello() {
		return "Hello World !!";
	}
}
