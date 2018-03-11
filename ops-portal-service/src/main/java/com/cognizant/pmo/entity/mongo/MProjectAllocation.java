/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author 238209
 *
 */
public class MProjectAllocation {
	
	private BigInteger assignmentId;
	private LocalDate assignmentStartDate;
	private LocalDate assignmentEndDate;
	private String assignmentStatus;
	private int assignmentPercentage;
	private BigInteger projectId;
	private boolean billable;
	
	/**
	 * @return the assignmentId
	 */
	public BigInteger getAssignmentId() {
		return assignmentId;
	}
	/**
	 * @param assignmentId the assignmentId to set
	 */
	public void setAssignmentId(BigInteger assignmentId) {
		this.assignmentId = assignmentId;
	}
	/**
	 * @return the assignmentStartDate
	 */
	public LocalDate getAssignmentStartDate() {
		return assignmentStartDate;
	}
	/**
	 * @param assignmentStartDate the assignmentStartDate to set
	 */
	public void setAssignmentStartDate(LocalDate assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}
	/**
	 * @return the assignmentEndDate
	 */
	public LocalDate getAssignmentEndDate() {
		return assignmentEndDate;
	}
	/**
	 * @param assignmentEndDate the assignmentEndDate to set
	 */
	public void setAssignmentEndDate(LocalDate assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}
	/**
	 * @return the assignmentStatus
	 */
	public String getAssignmentStatus() {
		return assignmentStatus;
	}
	/**
	 * @param assignmentStatus the assignmentStatus to set
	 */
	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}
	/**
	 * @return the assignmentPercentage
	 */
	public int getAssignmentPercentage() {
		return assignmentPercentage;
	}
	/**
	 * @param assignmentPercentage the assignmentPercentage to set
	 */
	public void setAssignmentPercentage(int assignmentPercentage) {
		this.assignmentPercentage = assignmentPercentage;
	}
	/**
	 * @return the projectId
	 */
	public BigInteger getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(BigInteger projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the billable
	 */
	public boolean isBillable() {
		return billable;
	}
	/**
	 * @param billable the billable to set
	 */
	public void setBillable(boolean billable) {
		this.billable = billable;
	}
	
}
