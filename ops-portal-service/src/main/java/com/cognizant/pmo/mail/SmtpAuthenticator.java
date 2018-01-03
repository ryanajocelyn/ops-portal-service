/**
 * 
 */
package com.cognizant.pmo.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 238209
 *
 */
@Component
public class SmtpAuthenticator extends Authenticator {
	
	@Value("${ldap.user}")
	private String username;
	
	@Value("${ldap.password}")
	private String password;
	
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
