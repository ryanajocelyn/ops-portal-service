/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.HolidayBo;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MHoliday;
import com.cognizant.pmo.repository.mongo.MAssociateRepository;
import com.cognizant.pmo.repository.mongo.MClarityRepository;
import com.cognizant.pmo.repository.mongo.MFieldGlassRepository;
import com.cognizant.pmo.repository.mongo.MHolidayRepository;
import com.cognizant.pmo.service.AssociateService;
import com.cognizant.pmo.utils.Utils;

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

	@Resource
	private MHolidayRepository mHolidayRepository;

	@Override
	public List<MAssociate> findAssociateByCriteria() {
		return massociateRepository.findAll();
	}

	@Override
	public boolean saveHoliday(HolidayBo holiday) {
		
		MHoliday mHoliday = new MHoliday();
		mHoliday.setDescription(holiday.getDescription());
		mHoliday.setDate(Utils.parseDate(holiday.getDate(), Utils.FORMAT_YYYY_MM_DD));
		mHoliday.setLocation(holiday.getLocation());
		
		MHoliday savedEntity = mHolidayRepository.save(mHoliday);
		
		boolean saved = false;
		if (savedEntity != null
				&& savedEntity.getId() != null
				&& StringUtils.isNotBlank(savedEntity.getId().toString())) {
			saved = true;
		}
				
		return saved;
	}

	@Override
	public boolean deleteHoliday(HolidayBo holiday) {
		return false;
	}

	@Override
	public List<HolidayBo> getAllHolidays() {
		List<MHoliday> holidaysList = mHolidayRepository.findAll();
		
		List<HolidayBo> holidayBoList = holidaysList.stream()
						.map(h -> {
							HolidayBo holidayBo = new HolidayBo();
							holidayBo.setDate(Utils.formatDate(h.getDate(), Utils.FORMAT_DD_MON_YYYY));
							holidayBo.setDescription(h.getDescription());
							holidayBo.setId(h.getId().toString());
							holidayBo.setLocation(h.getLocation());
							
							return holidayBo;
						})
						.collect(Collectors.toList());
		
		return holidayBoList;
	}

	@Override
	public List<MAssociate> findAssociateWithMissingXref() {
		return massociateRepository.findByResourceWorkdayIdNull();
	}
}
