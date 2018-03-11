/**
 * 
 */
package com.cognizant.pmo.business;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pmo.bo.AccountBo;
import com.cognizant.pmo.bo.Record;
import com.cognizant.pmo.entity.Account;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MProject;
import com.cognizant.pmo.repository.AccountRepository;
import com.cognizant.pmo.service.AssociateService;
import com.cognizant.pmo.service.FileProcessService;
import com.cognizant.pmo.service.ProjectService;
import com.cognizant.pmo.utils.ExcelFileReader;

/**
 * @author 238209
 *
 */
@RestController
public class FileProcessController extends AbstractController {

	@Autowired
	private FileProcessService fileProcessService;

	@Autowired
	private AssociateService associateService;

	@Autowired
	private ProjectService projectService;
	
	@Resource
	private AccountRepository accountRepository;
	
	@GetMapping("/process/fc")
	public @ResponseBody Iterable<Account> processFcReport() {
		String fileName = "D:/238209/Technical/tmp/ESI MO 02212018.xlsx";
		
		boolean fileLoaded = fileProcessService.readAndLoadFcReport(fileName);
		System.out.println(fileLoaded);
		
		return accountRepository.findAll();
	}
	
	@GetMapping("/process/clarity")
	public @ResponseBody Iterable<Account> processClarityReport() {
		String fileName = "D:/238209/Technical/tmp/test_clarity.xlsx";
		
		boolean fileLoaded = fileProcessService.readAndLoadClarityReport(fileName);
		System.out.println(fileLoaded);
		
		return accountRepository.findAll();
	}
	
	@GetMapping("/process/xref")
	public @ResponseBody Iterable<Account> processXrefReport() {
		String fileName = "D:/238209/Technical/tmp/xref_clarity.xlsx";
		
		boolean fileLoaded = fileProcessService.readAndLoadXRefReport(fileName);
		System.out.println(fileLoaded);
		
		return accountRepository.findAll();
	}

	@GetMapping("/process/allProjects")
	public @ResponseBody Iterable<MProject> createAllProjects() {
		List<MProject> projectList = getAllProjectData();
		
		for (MProject project : projectList) {
			boolean projectCreated = projectService.createProject(project);
			System.out.println(projectCreated);
		}
		
		return projectService.getAllProjects();
	}
	
	@GetMapping("/process/fg")
	public @ResponseBody Iterable<MProject> processFgReport() {
		String fileName = "D:/238209/Technical/tmp/test_fg.xlsx";
		
		boolean fgCreated = fileProcessService.readAndLoadFGReport(fileName);
		System.out.println(fgCreated);
		
		return projectService.getAllProjects();
	}

	private List<MProject> getAllProjectData() {
		String fileName = "D:/238209/Technical/tmp/project_list.xlsx";
		List<Record> projectList = ExcelFileReader.readFileWithHeader(fileName);

		List<MProject> retList = new ArrayList<MProject>();
		MProject project = null;
		for (Record record : projectList) {
			project = new MProject();
			project.setId(new BigInteger(record.getStringValue("_id")));
			project.setProjectName(record.getStringValue("projectName"));
			project.setStartDate(record.getDateValue("startDate"));
			project.setEndDate(record.getDateValue("endDate"));
			project.setAccountId(Long.valueOf(record.getStringValue("accountId")));
			project.setAccountName(record.getStringValue("accountName"));
			project.setPortfolioName(record.getStringValue("portfolioName"));
			project.setProjectType(record.getStringValue("projectType"));
			project.setProjectOwningDeptName(record.getStringValue("projectOwningDeptName"));
			project.setProjectOwningDeptType(record.getStringValue("projectOwningDeptType"));
			project.setProjectManagerId(record.getStringValue("projectManagerId"));
			
			retList.add(project);
		}
		
		return retList;
	}

	@GetMapping("/associate/all")
	public @ResponseBody Iterable<MAssociate> getAssociateByCriteria() {
		return associateService.findAssociateByCriteria();
	}
	
	@GetMapping("/accounts/all")
	public @ResponseBody Iterable<AccountBo> getAccounts() {
		return projectService.getAllProjectAccounts();
	}
}
