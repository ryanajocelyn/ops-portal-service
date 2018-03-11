/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
	
	private String resourceWorkdayId;
	private String associateName;
	private String resourceManagerName;
	private Long rmWorkdayId;
	private LocalDate weekStartDate;
	private LocalDate weekEndDate;
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
	private LocalDateTime submittedOn;
	
	private String rejectedBy;
	private LocalDateTime rejectedOn;
	
	private boolean isApproved;
	private String approvedBy;
	private LocalDateTime approvedOn;
	
	private double maxWeekHours;

	public void addHoursSun(Double doubleValue) {
		hoursSun += doubleValue;
	}

	public void addHoursMon(Double doubleValue) {
		hoursMon += doubleValue;
	}

	public void addHoursTue(Double doubleValue) {
		hoursTue += doubleValue;		
	}

	public void addHoursWed(Double doubleValue) {
		hoursWed += doubleValue;
	}

	public void addHoursThu(Double doubleValue) {
		hoursThu += doubleValue;
	}

	public void addHoursFri(Double doubleValue) {
		hoursFri += doubleValue;
	}

	public void addHoursSat(Double doubleValue) {
		hoursSat += doubleValue;		
	}

	public void addWeekTotal(Double doubleValue) {
		weekTotal += doubleValue;
	}
	
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
	public String getResourceWorkdayId() {
		return resourceWorkdayId;
	}

	/**
	 * @param resourceWorkdayId the resourceWorkdayId to set
	 */
	public void setResourceWorkdayId(String resourceWorkdayId) {
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
	 * @return the weekEndDate
	 */
	public LocalDate getWeekEndDate() {
		return weekEndDate;
	}

	/**
	 * @param weekEndDate the weekEndDate to set
	 */
	public void setWeekEndDate(LocalDate weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	/**
	 * @return the submittedOn
	 */
	public LocalDateTime getSubmittedOn() {
		return submittedOn;
	}

	/**
	 * @param submittedOn the submittedOn to set
	 */
	public void setSubmittedOn(LocalDateTime submittedOn) {
		this.submittedOn = submittedOn;
	}

	/**
	 * @return the rejectedOn
	 */
	public LocalDateTime getRejectedOn() {
		return rejectedOn;
	}

	/**
	 * @param rejectedOn the rejectedOn to set
	 */
	public void setRejectedOn(LocalDateTime rejectedOn) {
		this.rejectedOn = rejectedOn;
	}

	/**
	 * @return the approvedOn
	 */
	public LocalDateTime getApprovedOn() {
		return approvedOn;
	}

	/**
	 * @param approvedOn the approvedOn to set
	 */
	public void setApprovedOn(LocalDateTime approvedOn) {
		this.approvedOn = approvedOn;
	}

}
