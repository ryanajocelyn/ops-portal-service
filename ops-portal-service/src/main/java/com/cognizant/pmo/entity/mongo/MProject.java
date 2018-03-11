/**
 * 
 */
package com.cognizant.pmo.entity.mongo;

import java.math.BigInteger;
import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author 238209
 *
 */
@Document(collection="project")
public class MProject {

	@Id
	private BigInteger id;
	
	private String projectName;

	private LocalDate startDate;

	private LocalDate endDate;

	private String projectType;

	private Long accountId;

	private String accountName;

	private String portfolioName;

	private String projectOwningDeptName;

	private String projectOwningDeptType;
	
	private String projectManagerId;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
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
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * @param portfolioName the portfolioName to set
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * @return the projectOwningDeptName
	 */
	public String getProjectOwningDeptName() {
		return projectOwningDeptName;
	}

	/**
	 * @param projectOwningDeptName the projectOwningDeptName to set
	 */
	public void setProjectOwningDeptName(String projectOwningDeptName) {
		this.projectOwningDeptName = projectOwningDeptName;
	}

	/**
	 * @return the projectOwningDeptType
	 */
	public String getProjectOwningDeptType() {
		return projectOwningDeptType;
	}

	/**
	 * @param projectOwningDeptType the projectOwningDeptType to set
	 */
	public void setProjectOwningDeptType(String projectOwningDeptType) {
		this.projectOwningDeptType = projectOwningDeptType;
	}

	/**
	 * @return the projectManagerId
	 */
	public String getProjectManagerId() {
		return projectManagerId;
	}

	/**
	 * @param projectManagerId the projectManagerId to set
	 */
	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
}