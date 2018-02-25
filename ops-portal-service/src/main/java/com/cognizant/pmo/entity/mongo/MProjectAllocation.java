/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author 238209
 *
 */
public class MProjectAllocation {
	
	private BigInteger assignmentId;
	private Date assignmentStartDate;
	private Date assignmentEndDate;
	private String assignmentStatus;
	private int assignmentPercentage;
	private BigInteger projectId;
	
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
	public Date getAssignmentStartDate() {
		return assignmentStartDate;
	}
	/**
	 * @param assignmentStartDate the assignmentStartDate to set
	 */
	public void setAssignmentStartDate(Date assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}
	/**
	 * @return the assignmentEndDate
	 */
	public Date getAssignmentEndDate() {
		return assignmentEndDate;
	}
	/**
	 * @param assignmentEndDate the assignmentEndDate to set
	 */
	public void setAssignmentEndDate(Date assignmentEndDate) {
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
	
}
