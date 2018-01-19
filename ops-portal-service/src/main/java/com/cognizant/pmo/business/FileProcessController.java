/**
 * 
 */
package com.cognizant.pmo.business;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pmo.entity.Account;
import com.cognizant.pmo.repository.AccountRepository;
import com.cognizant.pmo.service.AssociateService;

/**
 * @author 238209
 *
 */
@RestController
public class FileProcessController extends AbstractController {

	@Autowired
	private AssociateService associateService;
	
	@Resource
	private AccountRepository accountRepository;
	
	@GetMapping("/process/fc")
	public @ResponseBody Iterable<Account> processFcReport() {
		String fileName = "D:/238209/Technical/tmp/test_fc.xlsx";
		
		boolean fileLoaded = associateService.readAndLoadFcReport(fileName);
		System.out.println(fileLoaded);
		
		return accountRepository.findAll();
	}
}
