/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.Record;
import com.cognizant.pmo.entity.Associate;
import com.cognizant.pmo.entity.Department;
import com.cognizant.pmo.entity.Grade;
import com.cognizant.pmo.repository.AssociateRepository;
import com.cognizant.pmo.service.AssociateService;
import com.cognizant.pmo.service.DepartmentService;
import com.cognizant.pmo.service.GradeService;
import com.cognizant.pmo.utils.ExcelFileReader;

/**
 * @author 238209
 *
 */
@Component
public class AssociateServiceImpl implements AssociateService {
	
	@Resource
	private AssociateRepository associateRepository;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	public boolean readAndLoadFcReport(String fileName) {
		List<Record> fcReportList = ExcelFileReader.readFileWithHeader(fileName);

		List<Associate> associateList = new ArrayList<Associate>();
		Associate associate = null;
		for (Record record : fcReportList) {
			associate = new Associate();
			
			associate.setAssociateId(record.getIntValue(FcReportHeaders.ASSOCIATE_ID.value()));
			associate.setAssociateName(record.getStringValue(FcReportHeaders.ASSOCIATE_NAME.value()));
			associate.setBgvStatus(record.getStringValue(FcReportHeaders.BGV.value()));
			associate.setDateOfJoining(record.getDateValue(FcReportHeaders.DOJ.value()));
			associate.setCity(record.getStringValue(FcReportHeaders.CITY.value()));
			associate.setCountry(record.getStringValue(FcReportHeaders.COUNTRY.value()));
			associate.setLocation(record.getStringValue(FcReportHeaders.LOCATION.value()));
			associate.setLocationId(record.getStringValue(FcReportHeaders.LOCATION_ID.value()));
			associate.setState(record.getStringValue(FcReportHeaders.STATE.value()));
			associate.setFgWorkerId(record.getStringValue(FcReportHeaders.FG_WORKER_ID.value()));
			associate.setFgTerminationDate(record.getDateValue(FcReportHeaders.FG_TERMINATION_DATE.value()));
			
			Grade grade = gradeService.getGradeByShortName(
											record.getStringValue(FcReportHeaders.GRADE.value()));
			associate.setGrade(grade);
			
			String deptName = record.getStringValue(FcReportHeaders.DEPARTMENT_NAME.value());
			Department department = departmentService.getDepartmentByDepartmentName(deptName);
			associate.setDepartment(department);
			
			associateList.add(associate);
		}
		
		associateRepository.save(associateList);
		return false;
	}
}
