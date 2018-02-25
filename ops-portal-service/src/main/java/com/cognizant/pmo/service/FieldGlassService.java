/**
 * 
 */
package com.cognizant.pmo.service;

import java.util.Date;
import java.util.List;

import com.cognizant.pmo.bo.Deviations;

/**
 * @author 238209
 *
 */
public interface FieldGlassService {

	public List<Deviations> getEsaVsFgDeviations(List<Long> accountIdList, Date startDate, Date endDate);
}
