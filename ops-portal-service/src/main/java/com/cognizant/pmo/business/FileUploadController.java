/**
 * 
 */
package com.cognizant.pmo.business;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.AttachmentCollection;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.ItemId;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

/**
 * @author lenovo
 *
 */
@RestController
public class FileUploadController extends AbstractController {

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private Authenticator ldapAuth;
	
	@PostMapping("/file/upload")
	public void fileUpload(@RequestParam("file") MultipartFile uploadFile) {
		
	}
	
	@GetMapping("/file/download")
	public String downloadFromEmail() {
		try {
			ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP1);
			service.setUrl(new URI("https://mail.cognizant.com/ews/Exchange.asmx"));
			
			ExchangeCredentials credentials = new WebCredentials("238209", "Jan-2018", "cts");
	        service.setCredentials(credentials);
	        
	        readEmails(service);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}
	
	public List readEmails(ExchangeService service) {
        List msgDataList = new ArrayList();
        try {
            Folder folder = Folder.bind(service, WellKnownFolderName.Inbox);
            FindItemsResults results = service.findItems(folder.getId(), new ItemView(5));
            
            List<Item> items = results.getItems();
            int i = 1;
            for (Item item : items) {
                Map messageData = new HashMap();
                messageData = readEmailItem(service, item.getId());
                System.out.println("\nEmails #" + (i++) + ":");
                System.out.println("subject : " + messageData.get("subject").toString());
                System.out.println("Sender : " + messageData.get("senderName").toString());
                msgDataList.add(messageData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgDataList;
    }
	
	/**
     * Reading one email at a time. Using Item ID of the email.
     * Creating a message data map as a return value.
	 * @param service 
     */
    public Map readEmailItem(ExchangeService service, ItemId itemId) {
        Map messageData = new HashMap();
        try {
            Item itm = Item.bind(service, itemId, PropertySet.FirstClassProperties);
            EmailMessage emailMessage = EmailMessage.bind(service, itm.getId());
            messageData.put("emailItemId", emailMessage.getId().toString());
            messageData.put("subject", emailMessage.getSubject().toString());
            messageData.put("fromAddress", emailMessage.getFrom().getAddress().toString());
            messageData.put("senderName", emailMessage.getSender().getName().toString());
            Date dateTimeCreated = emailMessage.getDateTimeCreated();
            messageData.put("SendDate", dateTimeCreated.toString());
            Date dateTimeRecieved = emailMessage.getDateTimeReceived();
            messageData.put("RecievedDate", dateTimeRecieved.toString());
            messageData.put("Size", emailMessage.getSize() + "");
            messageData.put("emailBody", emailMessage.getBody().toString());
            
            if (emailMessage.getHasAttachments()) {
            	AttachmentCollection attachmentColl = itm.getAttachments();
            	System.out.println(attachmentColl.getCount());
            	
            	for (int i = 0; i < attachmentColl.getCount(); i++) { 
                    FileAttachment attachment = (FileAttachment)attachmentColl.getPropertyAtIndex(i);
                    System.out.println(attachment.getName());
                    String name = attachment.getName();
                    attachment.load();
                    
                    if (name.endsWith("xlsx")) {
                    byte[] content = attachment.getContent();
                    File file = new File("D:\\238209\\Technical\\tmp\\" + attachment.getName());
                    FileUtils.writeByteArrayToFile(file, content);
                    }
                 } 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageData;
    }
	
	public String downloadFromEmailPop() {
		String from = "CogESHCRSA@cognizant.com";
		Properties props = new Properties();
		props.put("mail.smtp.host", "apacsmtp.cts.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", "abijeeth.darwin@cognizant.com");
		props.setProperty("mail.user", "abijeeth.darwin@cognizant.com");
		props.setProperty("mail.password", "Jan-2018");
		props.setProperty("mail.store.protocol", "imap");
		
		Session session = Session.getInstance(props);
		//session.setDebug(debug);
		try {
			Store store = session.getStore("pop3");
			store.connect("seg.cognizant.com", "238209", "Jan-2018");
			
			System.out.println(store.isConnected());
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "success";
	}
	
}
