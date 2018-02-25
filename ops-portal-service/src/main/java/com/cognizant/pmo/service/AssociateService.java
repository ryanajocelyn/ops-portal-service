/**
 * 
 */
package com.cognizant.pmo.service;

import java.util.List;

import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.entity.mongo.MAssociate;

/**
 * @author 238209
 *
 */
public interface AssociateService {

	public List<MAssociate> findAssociateByCriteria();
	
	public List<Deviations> calculateDeviations();
}
