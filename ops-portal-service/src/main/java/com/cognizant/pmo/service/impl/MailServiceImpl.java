/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.cognizant.pmo.service.IMailService;

/**
 * @author 238209
 *
 */
public class MailServiceImpl implements IMailService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			String host = "ctsinchnvcasa.cts.com";
			String user = "238209";
			String password = "Jan-2018";

			//create properties field
			Properties properties = new Properties();
			// ctsinchnvcasa.cts.com
			properties.put("mail.pop3.host", "mail.cognizant.com");
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			//create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
