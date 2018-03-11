/**
 * 
 */
package com.cognizant.pmo.service;

import java.util.List;

import com.cognizant.pmo.bo.HolidayBo;
import com.cognizant.pmo.entity.mongo.MAssociate;

/**
 * @author 238209
 *
 */
public interface AssociateService {

	public List<MAssociate> findAssociateByCriteria();
	
	public boolean saveHoliday(HolidayBo holiday);
	
	public boolean deleteHoliday(HolidayBo holiday);
	
	public List<HolidayBo> getAllHolidays();
	
	public List<MAssociate> findAssociateWithMissingXref();
}
