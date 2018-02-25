/**
 * 
 */
package com.cognizant.pmo.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.cognizant.pmo.entity.mongo.MClarity;

/**
 * @author 238209
 *
 */
public class AssociateClarityModel {

	private List<MClarity> mClarityList;
	
	public AssociateClarityModel(List<MClarity> mClarityList) {
		this.mClarityList = mClarityList;
	}
	
	public Double getApprovedClarityHours(Date startDate, Date endDate) {
		
		double approvedHoursPerWeek = 0; 
		for (MClarity clarity : mClarityList) {
			approvedHoursPerWeek += getApprovedClarityHours(clarity, startDate, endDate);
		}
		
		return approvedHoursPerWeek;
	}

	private Double getApprovedClarityHours(MClarity clarity, Date startDate, Date endDate) {
		double approvedHours = 0;
		
		if (clarity.isApproved()) {
			List<Double> hoursForWeek = getHoursForWeek(clarity);
			Date weekCurrDate = new Date(clarity.getWeekStartDate().getTime());
			for (Double hours : hoursForWeek) {
				if (DateUtils.isSameDay(startDate, weekCurrDate) 
						|| DateUtils.isSameDay(endDate, weekCurrDate)
						|| (startDate.after(weekCurrDate) && endDate.before(weekCurrDate)) ) {
					approvedHours += hours;
				}
				
				weekCurrDate = DateUtils.addDays(weekCurrDate, 1);
			}
		}
		
		return approvedHours;
	}

	private List<Double> getHoursForWeek(MClarity clarity) {
		List<Double> hoursForWeek = new ArrayList<Double>();
		
		hoursForWeek.add(clarity.getHoursSun());
		hoursForWeek.add(clarity.getHoursMon());
		hoursForWeek.add(clarity.getHoursTue());
		hoursForWeek.add(clarity.getHoursWed());
		hoursForWeek.add(clarity.getHoursThu());
		hoursForWeek.add(clarity.getHoursFri());
		hoursForWeek.add(clarity.getHoursSat());
		
		return hoursForWeek;
	}
}
