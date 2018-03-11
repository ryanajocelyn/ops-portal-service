/**
 * 
 */
package com.cognizant.pmo.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.utils.Constants;
import com.cognizant.pmo.utils.Utils;

/**
 * @author 238209
 *
 */
public class AssociateClarityModel {

	private Deviations deviation;

	private double approvedClarityBillableHours;
	
	private double approvedUnpaidTimeoffHours;
	
	private double approvedFgBillableHours;
	
	private double minBillableHours;
	
	public AssociateClarityModel(Deviations deviation) {
		this.deviation = deviation;
		
		approvedClarityBillableHours = 0;
		approvedUnpaidTimeoffHours = 0;
		approvedFgBillableHours = 0;
		
		minBillableHours = 0;
	}
	
	public void calculate(LocalDate startDate, LocalDate endDate) {
		List<MClarity> mClarityList = deviation.getClarityList();
		
		for (MClarity clarity : mClarityList) {
			
			if (StringUtils.contains(clarity.getEsiProjectTitle(), Constants.UNPAID_TEXT)) {
				approvedUnpaidTimeoffHours += getApprovedClarityHours(clarity, startDate, endDate);
			} else {
				approvedClarityBillableHours += getApprovedClarityHours(clarity, startDate, endDate);
			}
		}
		
		
		List<MFieldGlass> mFgList = deviation.getFgList();
		if (mFgList != null) {
			for (MFieldGlass mFieldGlass : mFgList) {
				approvedFgBillableHours += getApprovedFgHours(mFieldGlass, startDate, endDate);
			}
		} 
	}

	public static double getTotalHoursPerWeek (MClarity mClarity) {
		double hoursPerWeek = 0;
		
		hoursPerWeek += mClarity.getHoursSun();
		hoursPerWeek += mClarity.getHoursMon();
		hoursPerWeek += mClarity.getHoursTue();
		hoursPerWeek += mClarity.getHoursWed();
		hoursPerWeek += mClarity.getHoursThu();
		hoursPerWeek += mClarity.getHoursFri();
		hoursPerWeek += mClarity.getHoursSat();
		
		return hoursPerWeek;
	}
	
	private Double getApprovedClarityHours(MClarity clarity, LocalDate startDate, LocalDate endDate) {
		double approvedHours = 0;
		
		if (clarity.isApproved() 
				&& Utils.isDateBetween(clarity.getWeekStartDate(), startDate, endDate)) {
			List<Double> hoursForWeek = getHoursForWeek(clarity);

			for (Double hours : hoursForWeek) {
				approvedHours += hours;
			}
		}
		
		return approvedHours;
	}
	
	private Double getApprovedFgHours(MFieldGlass fg, LocalDate startDate, LocalDate endDate) {
		double approvedHours = 0;
		
		if ( Utils.isDateBetween(fg.getFgStartDate(), startDate, endDate) ) {
			approvedHours += fg.getBillableHours();
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

	/**
	 * @return the approvedClarityBillableHours
	 */
	public double getApprovedClarityBillableHours() {
		return approvedClarityBillableHours;
	}

	/**
	 * @param approvedClarityBillableHours the approvedClarityBillableHours to set
	 */
	public void setApprovedClarityBillableHours(double approvedClarityBillableHours) {
		this.approvedClarityBillableHours = approvedClarityBillableHours;
	}

	/**
	 * @return the approvedUnpaidTimeoffHours
	 */
	public double getApprovedUnpaidTimeoffHours() {
		return approvedUnpaidTimeoffHours;
	}

	/**
	 * @param approvedUnpaidTimeoffHours the approvedUnpaidTimeoffHours to set
	 */
	public void setApprovedUnpaidTimeoffHours(double approvedUnpaidTimeoffHours) {
		this.approvedUnpaidTimeoffHours = approvedUnpaidTimeoffHours;
	}

	/**
	 * @return the minBillableHours
	 */
	public double getMinBillableHours() {
		return minBillableHours;
	}

	/**
	 * @param minBillableHours the minBillableHours to set
	 */
	public void setMinBillableHours(double minBillableHours) {
		this.minBillableHours = minBillableHours;
	}

	/**
	 * @return the approvedFgBillableHours
	 */
	public double getApprovedFgBillableHours() {
		return approvedFgBillableHours;
	}

	/**
	 * @param approvedFgBillableHours the approvedFgBillableHours to set
	 */
	public void setApprovedFgBillableHours(double approvedFgBillableHours) {
		this.approvedFgBillableHours = approvedFgBillableHours;
	}
}
