/**
 * 
 */
package com.cognizant.pmo.bo;

import java.io.Serializable;

/**
 * @author 238209
 *
 */
public class LoginRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3542641015730384317L;

	private String email;
	
	private String password;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
