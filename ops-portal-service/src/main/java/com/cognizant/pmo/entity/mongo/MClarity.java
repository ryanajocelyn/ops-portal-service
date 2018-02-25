/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 238209
 *
 */
@Document(collection="clarity")
public class MClarity {

	@Id
	private BigInteger id;
	
	private Long resourceWorkdayId;
	private String associateName;
	private String resourceManagerName;
	private Long rmWorkdayId;
	private Date weekStartDate;
	private Date weekEndDate;
	private String esiProjectId;
	private String esiProjectTitle;
	private String esiTaskId;
	private String esiTaskTitle;
	
	private double hoursSun;
	private double hoursMon;
	private double hoursTue;
	private double hoursWed;
	private double hoursThu;
	private double hoursFri;
	private double hoursSat;
	
	private double weekTotal;
	private double rate;
	
	private String submiitedBy;
	private Date submittedOn;
	
	private String rejectedBy;
	private Date rejectedOn;
	
	private boolean isApproved;
	private String approvedBy;
	private Date approvedOn;
	
	private double maxWeekHours;

	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}

	/**
	 * @return the resourceWorkdayId
	 */
	public Long getResourceWorkdayId() {
		return resourceWorkdayId;
	}

	/**
	 * @param resourceWorkdayId the resourceWorkdayId to set
	 */
	public void setResourceWorkdayId(Long resourceWorkdayId) {
		this.resourceWorkdayId = resourceWorkdayId;
	}

	/**
	 * @return the associateName
	 */
	public String getAssociateName() {
		return associateName;
	}

	/**
	 * @param associateName the associateName to set
	 */
	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	/**
	 * @return the resourceManagerName
	 */
	public String getResourceManagerName() {
		return resourceManagerName;
	}

	/**
	 * @param resourceManagerName the resourceManagerName to set
	 */
	public void setResourceManagerName(String resourceManagerName) {
		this.resourceManagerName = resourceManagerName;
	}

	/**
	 * @return the rmWorkdayId
	 */
	public Long getRmWorkdayId() {
		return rmWorkdayId;
	}

	/**
	 * @param rmWorkdayId the rmWorkdayId to set
	 */
	public void setRmWorkdayId(Long rmWorkdayId) {
		this.rmWorkdayId = rmWorkdayId;
	}

	/**
	 * @return the weekStartDate
	 */
	public Date getWeekStartDate() {
		return weekStartDate;
	}

	/**
	 * @param weekStartDate the weekStartDate to set
	 */
	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	/**
	 * @return the weekEndDate
	 */
	public Date getWeekEndDate() {
		return weekEndDate;
	}

	/**
	 * @param weekEndDate the weekEndDate to set
	 */
	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	/**
	 * @return the esiProjectId
	 */
	public String getEsiProjectId() {
		return esiProjectId;
	}

	/**
	 * @param esiProjectId the esiProjectId to set
	 */
	public void setEsiProjectId(String esiProjectId) {
		this.esiProjectId = esiProjectId;
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
	 * @return the esiTaskId
	 */
	public String getEsiTaskId() {
		return esiTaskId;
	}

	/**
	 * @param esiTaskId the esiTaskId to set
	 */
	public void setEsiTaskId(String esiTaskId) {
		this.esiTaskId = esiTaskId;
	}

	/**
	 * @return the esiTaskTitle
	 */
	public String getEsiTaskTitle() {
		return esiTaskTitle;
	}

	/**
	 * @param esiTaskTitle the esiTaskTitle to set
	 */
	public void setEsiTaskTitle(String esiTaskTitle) {
		this.esiTaskTitle = esiTaskTitle;
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
	 * @return the weekTotal
	 */
	public double getWeekTotal() {
		return weekTotal;
	}

	/**
	 * @param weekTotal the weekTotal to set
	 */
	public void setWeekTotal(double weekTotal) {
		this.weekTotal = weekTotal;
	}

	/**
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the submiitedBy
	 */
	public String getSubmiitedBy() {
		return submiitedBy;
	}

	/**
	 * @param submiitedBy the submiitedBy to set
	 */
	public void setSubmiitedBy(String submiitedBy) {
		this.submiitedBy = submiitedBy;
	}

	/**
	 * @return the submittedOn
	 */
	public Date getSubmittedOn() {
		return submittedOn;
	}

	/**
	 * @param submittedOn the submittedOn to set
	 */
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}

	/**
	 * @return the rejectedBy
	 */
	public String getRejectedBy() {
		return rejectedBy;
	}

	/**
	 * @param rejectedBy the rejectedBy to set
	 */
	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	/**
	 * @return the rejectedOn
	 */
	public Date getRejectedOn() {
		return rejectedOn;
	}

	/**
	 * @param rejectedOn the rejectedOn to set
	 */
	public void setRejectedOn(Date rejectedOn) {
		this.rejectedOn = rejectedOn;
	}

	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	/**
	 * @return the approvedBy
	 */
	public String getApprovedBy() {
		return approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	/**
	 * @return the approvedOn
	 */
	public Date getApprovedOn() {
		return approvedOn;
	}

	/**
	 * @param approvedOn the approvedOn to set
	 */
	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	/**
	 * @return the maxWeekHours
	 */
	public double getMaxWeekHours() {
		return maxWeekHours;
	}

	/**
	 * @param maxWeekHours the maxWeekHours to set
	 */
	public void setMaxWeekHours(double maxWeekHours) {
		this.maxWeekHours = maxWeekHours;
	}
}
