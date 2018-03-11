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
@Document(collection="fieldglass")
public class MFieldGlass {

	@Id
	private BigInteger id;
	
	private String fgWorkerId;
	private String fgWorkerName;
	private String invoiceStatus;
	private LocalDate fgStartDate;
	private LocalDate fgEndDate;
	private String workerLocation;
	private Double billableHours;
	private Double invoiceAmount;
	private Double fgRate;
	private String fgJobPostingTitle;
	

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
	 * @return the fgWorkerId
	 */
	public String getFgWorkerId() {
		return fgWorkerId;
	}


	/**
	 * @param fgWorkerId the fgWorkerId to set
	 */
	public void setFgWorkerId(String fgWorkerId) {
		this.fgWorkerId = fgWorkerId;
	}


	/**
	 * @return the fgWorkerName
	 */
	public String getFgWorkerName() {
		return fgWorkerName;
	}


	/**
	 * @param fgWorkerName the fgWorkerName to set
	 */
	public void setFgWorkerName(String fgWorkerName) {
		this.fgWorkerName = fgWorkerName;
	}


	/**
	 * @return the fgStartDate
	 */
	public LocalDate getFgStartDate() {
		return fgStartDate;
	}


	/**
	 * @param fgStartDate the fgStartDate to set
	 */
	public void setFgStartDate(LocalDate fgStartDate) {
		this.fgStartDate = fgStartDate;
	}


	/**
	 * @return the fgEndDate
	 */
	public LocalDate getFgEndDate() {
		return fgEndDate;
	}


	/**
	 * @param fgEndDate the fgEndDate to set
	 */
	public void setFgEndDate(LocalDate fgEndDate) {
		this.fgEndDate = fgEndDate;
	}


	/**
	 * @return the billableHours
	 */
	public Double getBillableHours() {
		return billableHours;
	}


	/**
	 * @param billableHours the billableHours to set
	 */
	public void setBillableHours(Double billableHours) {
		this.billableHours = billableHours;
	}


	/**
	 * @return the invoiceAmount
	 */
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}


	/**
	 * @param invoiceAmount the invoiceAmount to set
	 */
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}


	/**
	 * @return the fgRate
	 */
	public Double getFgRate() {
		return fgRate;
	}


	/**
	 * @param fgRate the fgRate to set
	 */
	public void setFgRate(Double fgRate) {
		this.fgRate = fgRate;
	}


	/**
	 * @return the fgJobPostingTitle
	 */
	public String getFgJobPostingTitle() {
		return fgJobPostingTitle;
	}


	/**
	 * @param fgJobPostingTitle the fgJobPostingTitle to set
	 */
	public void setFgJobPostingTitle(String fgJobPostingTitle) {
		this.fgJobPostingTitle = fgJobPostingTitle;
	}


	/**
	 * @return the invoiceStatus
	 */
	public String getInvoiceStatus() {
		return invoiceStatus;
	}


	/**
	 * @param invoiceStatus the invoiceStatus to set
	 */
	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}


	/**
	 * @return the workerLocation
	 */
	public String getWorkerLocation() {
		return workerLocation;
	}


	/**
	 * @param workerLocation the workerLocation to set
	 */
	public void setWorkerLocation(String workerLocation) {
		this.workerLocation = workerLocation;
	}
}