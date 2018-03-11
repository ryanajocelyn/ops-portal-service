/**
 * 
 */
package com.cognizant.pmo.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.pmo.entity.mongo.MClarity;

/**
 * @author 238209
 *
 */
public class DeviationDetail {
	
	private LocalDate weekStartDate;
	
	private double hoursSun;
	private double hoursMon;
	private double hoursTue;
	private double hoursWed;
	private double hoursThu;
	private double hoursFri;
	private double hoursSat;

	private double hoursTotal;
	
	private double clarityNonBillable;
	
	private double clarityUnapproved;
	
	private double fieldGlassBillableHours;

	private boolean clarityApproved;
	
	private String fgTerminationDate;
	private String weekStartDateStr;
	
	private String esiProjectTitle;
	
	private List<DeviationDetail> subDetails;
	
	public void addFieldGlassBillableHours(Double billableHours) {
		if (billableHours != null) {
			this.fieldGlassBillableHours += billableHours;
		}
	}

	public void calculateTotalBillableHours() {
		hoursTotal = hoursSun + hoursMon + hoursTue + hoursWed
						+ hoursThu + hoursFri + hoursSat;
	}

	public void addDeviationSubDetail(MClarity mClarity) {
		DeviationDetail detail = new DeviationDetail();
		detail.setWeekStartDate(mClarity.getWeekStartDate());
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		detail.setWeekStartDateStr(df.format(mClarity.getWeekStartDate()));
		
		detail.setEsiProjectTitle(mClarity.getEsiProjectTitle());
		
		detail.setHoursSun(mClarity.getHoursSun());
		detail.setHoursMon(mClarity.getHoursMon());
		detail.setHoursTue(mClarity.getHoursTue());
		detail.setHoursWed(mClarity.getHoursWed());
		detail.setHoursThu(mClarity.getHoursThu());
		detail.setHoursFri(mClarity.getHoursFri());
		detail.setHoursSat(mClarity.getHoursSat());
		
		getSubDetails().add(detail);
	}

	/**
	 * @return the weekStartDate
	 */
	public LocalDate getWeekStartDate() {
		return weekStartDate;
	}

	/**
	 * @param weekStartDate the weekStartDate to set
	 */
	public void setWeekStartDate(LocalDate weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	/**
	 * @return the hoursSun
	 */
	public double getHoursSun() {
		return hoursSun;
	}

	/**
	 * @param hoursSun the hoursSun to set
	 */
	public void setHoursSun(double hoursSun) {
		this.hoursSun = hoursSun;
	}

	/**
	 * @return the hoursMon
	 */
	public double getHoursMon() {
		return hoursMon;
	}

	/**
	 * @param hoursMon the hoursMon to set
	 */
	public void setHoursMon(double hoursMon) {
		this.hoursMon = hoursMon;
	}

	/**
	 * @return the hoursTue
	 */
	public double getHoursTue() {
		return hoursTue;
	}

	/**
	 * @param hoursTue the hoursTue to set
	 */
	public void setHoursTue(double hoursTue) {
		this.hoursTue = hoursTue;
	}

	/**
	 * @return the hoursWed
	 */
	public double getHoursWed() {
		return hoursWed;
	}

	/**
	 * @param hoursWed the hoursWed to set
	 */
	public void setHoursWed(double hoursWed) {
		this.hoursWed = hoursWed;
	}

	/**
	 * @return the hoursThu
	 */
	public double getHoursThu() {
		return hoursThu;
	}

	/**
	 * @param hoursThu the hoursThu to set
	 */
	public void setHoursThu(double hoursThu) {
		this.hoursThu = hoursThu;
	}

	/**
	 * @return the hoursFri
	 */
	public double getHoursFri() {
		return hoursFri;
	}

	/**
	 * @param hoursFri the hoursFri to set
	 */
	public void setHoursFri(double hoursFri) {
		this.hoursFri = hoursFri;
	}

	/**
	 * @return the hoursSat
	 */
	public double getHoursSat() {
		return hoursSat;
	}

	/**
	 * @param hoursSat the hoursSat to set
	 */
	public void setHoursSat(double hoursSat) {
		this.hoursSat = hoursSat;
	}

	/**
	 * @return the clarityNonBillable
	 */
	public double getClarityNonBillable() {
		return clarityNonBillable;
	}

	/**
	 * @param clarityNonBillable the clarityNonBillable to set
	 */
	public void setClarityNonBillable(double clarityNonBillable) {
		this.clarityNonBillable = clarityNonBillable;
	}

	/**
	 * @return the clarityUnapproved
	 */
	public double getClarityUnapproved() {
		return clarityUnapproved;
	}

	/**
	 * @param clarityUnapproved the clarityUnapproved to set
	 */
	public void setClarityUnapproved(double clarityUnapproved) {
		this.clarityUnapproved = clarityUnapproved;
	}

	/**
	 * @return the fieldGlassBillableHours
	 */
	public double getFieldGlassBillableHours() {
		return fieldGlassBillableHours;
	}

	/**
	 * @param fieldGlassBillableHours the fieldGlassBillableHours to set
	 */
	public void setFieldGlassBillableHours(double fieldGlassBillableHours) {
		this.fieldGlassBillableHours = fieldGlassBillableHours;
	}

	/**
	 * @return the clarityApproved
	 */
	public boolean isClarityApproved() {
		return clarityApproved;
	}

	/**
	 * @param clarityApproved the clarityApproved to set
	 */
	public void setClarityApproved(boolean clarityApproved) {
		this.clarityApproved = clarityApproved;
	}

	/**
	 * @return the weekStartDateStr
	 */
	public String getWeekStartDateStr() {
		return weekStartDateStr;
	}

	/**
	 * @param weekStartDateStr the weekStartDateStr to set
	 */
	public void setWeekStartDateStr(String weekStartDateStr) {
		this.weekStartDateStr = weekStartDateStr;
	}

	/**
	 * @param fgTerminationDate the fgTerminationDate to set
	 */
	public void setFgTerminationDate(String fgTerminationDate) {
		this.fgTerminationDate = fgTerminationDate;
	}

	/**
	 * @return the fgTerminationDate
	 */
	public String getFgTerminationDate() {
		return fgTerminationDate;
	}

	/**
	 * @return the hoursTotal
	 */
	public double getHoursTotal() {
		return hoursTotal;
	}

	/**
	 * @param hoursTotal the hoursTotal to set
	 */
	public void setHoursTotal(double hoursTotal) {
		this.hoursTotal = hoursTotal;
	}

	/**
	 * @return the esiProjectTitle
	 */
	public String getEsiProjectTitle() {
		return esiProjectTitle;
	}

	/**
	 * @param esiProjectTitle the esiProjectTitle to set
	 */
	public void setEsiProjectTitle(String esiProjectTitle) {
		this.esiProjectTitle = esiProjectTitle;
	}

	/**
	 * @return the subDetails
	 */
	public List<DeviationDetail> getSubDetails() {
		if (subDetails == null) {
			subDetails = new ArrayList<DeviationDetail>();
		}
		
		return subDetails;
	}

	/**
	 * @param subDetails the subDetails to set
	 */
	public void setSubDetails(List<DeviationDetail> subDetails) {
		this.subDetails = subDetails;
	}
}
