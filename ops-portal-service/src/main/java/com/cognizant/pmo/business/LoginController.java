/**
 * 
 */
package com.cognizant.pmo.business;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pmo.bo.LoginRequest;
import com.cognizant.pmo.bo.LoginResponse;

/**
 * @author 238209
 *
 */
@RestController
public class LoginController extends AbstractController {
	
	@PostMapping("/login/auth")
	public LoginResponse doLogin(@RequestBody LoginRequest request) {
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		
		LoginResponse response = new LoginResponse();
		response.setSuccess(true);
		
		return response;
	}
}
