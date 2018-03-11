/**
 * 
 */
package com.cognizant.pmo.service;

import java.time.LocalDate;
import java.util.List;

import com.cognizant.pmo.bo.AssociateUBR;
import com.cognizant.pmo.bo.Deviations;

/**
 * @author 238209
 *
 */
public interface FieldGlassService {

	public List<Deviations> getEsaVsFgDeviations(List<Long> accountIdList, LocalDate startDate, LocalDate endDate);

	public List<AssociateUBR> getUBRDetails(List<Long> accountIdList, String month);
}
