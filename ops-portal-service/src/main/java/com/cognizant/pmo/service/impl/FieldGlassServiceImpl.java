/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.AssociateClarityModel;
import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.entity.mongo.MProject;
import com.cognizant.pmo.repository.mongo.MAssociateRepository;
import com.cognizant.pmo.repository.mongo.MClarityRepository;
import com.cognizant.pmo.repository.mongo.MFieldGlassRepository;
import com.cognizant.pmo.repository.mongo.MProjectRepository;
import com.cognizant.pmo.service.FieldGlassService;

/**
 * @author 238209
 *
 */
@Component
public class FieldGlassServiceImpl implements FieldGlassService {

	@Resource
	private MAssociateRepository mAssociateRepository;
	
	@Resource
	private MProjectRepository mProjectRepository;
	
	@Resource 
	private MClarityRepository mClarityRepository;
	
	@Resource
	private MFieldGlassRepository mFieldGlassRepository;
	
	@Override
	public List<Deviations> getEsaVsFgDeviations(List<Long> accountIdList, Date startDate, Date endDate) {
		
		List<BigInteger> projectIdList = getProjectsForAccounts(accountIdList);
		
		List<MAssociate> associateList = 
				mAssociateRepository.findByProjectIdQuery(projectIdList);
		
		List<Deviations> deviationsList = new ArrayList<Deviations>();
		
		associateList.forEach(associate -> {
			Deviations deviation = getClarityAndFgDetails(associate);
			
			calculateDeviations(deviationsList, startDate, endDate);
			
			deviationsList.add(deviation);
		});
		
		return deviationsList;
	}

	private Deviations getClarityAndFgDetails(MAssociate associate) {
		Deviations deviation = new Deviations();
		List<MClarity> clarityList = 
				mClarityRepository.findByResourceIdQuery(associate.getResourceWorkdayId());
		
		deviation.setAssociate(associate);
		deviation.setClarityList(clarityList);
		
		List<MFieldGlass> fgList = 
				mFieldGlassRepository.findByResourceIdQuery(associate.getResourceWorkdayId().toString());
		
		deviation.setFgList(fgList);
		return deviation;
	}

	private void calculateDeviations(List<Deviations> deviationsList, Date startDate, Date endDate) {

		deviationsList.forEach(deviation -> {
			List<MClarity> clarityList = deviation.getClarityList();
			AssociateClarityModel clarityModel = new AssociateClarityModel(clarityList);

			deviation.setClarityBillableHours(clarityModel.getApprovedClarityHours(startDate, endDate));
		});
	}

	private List<BigInteger> getProjectsForAccounts(List<Long> accountIdList) {
		List<MProject> projectList = new ArrayList<MProject>();
		
		accountIdList.forEach(accountId -> {
			projectList.addAll(mProjectRepository.findByAccountId(accountId));
		});
		
		List<BigInteger> projectIdList = new ArrayList<BigInteger>();
		projectList.forEach(project -> projectIdList.add(project.getId()));
		
		return projectIdList;
	}

}
