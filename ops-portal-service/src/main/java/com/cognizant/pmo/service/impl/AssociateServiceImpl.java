/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.repository.mongo.MAssociateRepository;
import com.cognizant.pmo.repository.mongo.MClarityRepository;
import com.cognizant.pmo.repository.mongo.MFieldGlassRepository;
import com.cognizant.pmo.service.AssociateService;

/**
 * @author 238209
 *
 */
@Component
public class AssociateServiceImpl implements AssociateService {
	
	@Resource
	private MAssociateRepository massociateRepository;
	
	@Resource
	private MClarityRepository mClarityRepository;
	
	@Resource
	private MFieldGlassRepository mFgRepository;
	
	@Override
	public List<MAssociate> findAssociateByCriteria() {
		return massociateRepository.findAll();
	}

	@Override
	public List<Deviations> calculateDeviations() {
		List<MAssociate> associateList = massociateRepository.findAll();
		
		List<Deviations> deviationsList = new ArrayList<Deviations>();
		Deviations deviation = null;
		for (MAssociate mAssociate : associateList) {
			deviation = new Deviations();
			
			deviation.setAssociate(mAssociate);
			Long resWorkdayId = mAssociate.getResourceWorkdayId();
			if (resWorkdayId != null && resWorkdayId > 0) {
				List<MClarity> clarityList = mClarityRepository.findByResourceIdQuery(resWorkdayId);
				Map<Date, MFieldGlass> fgMap = mapFieldGlassByDate(mAssociate.getFgWorkerId());
				
				for (MClarity mClarity : clarityList) {
					MFieldGlass fieldGlass = fgMap.get(mClarity.getWeekStartDate());
					
					if (fieldGlass != null) {
						Double fgHours = fieldGlass.getBillableHours();
						//Double clarityHours = mClarity.get
					}
				}
			}
		}
		
		return deviationsList;
	}

	private Map<Date, MFieldGlass> mapFieldGlassByDate(String fgWorkerId) {
		List<MFieldGlass> fgList = mFgRepository.findByResourceIdQuery(fgWorkerId);
		
		Map<Date, MFieldGlass> fgMap = new HashMap<Date, MFieldGlass>();
		for (MFieldGlass mFieldGlass : fgList) {
			fgMap.put(mFieldGlass.getFgStartDate(), mFieldGlass);
		}
		
		return fgMap;
	}
}
