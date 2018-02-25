/**
 * 
 */
package com.cognizant.pmo.bo;

import java.util.List;

import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;

/**
 * @author 238209
 *
 */
public class Deviations {

	private MAssociate associate;
	private Double minimumBillableHours;
	private Double fgClarityDeviation;
	private Double fgMinBillableDeviation;
	private Double fgBillableHours;
	private Double clarityBillableHours;

	private List<MClarity> clarityList;
	private List<MFieldGlass> fgList;
	
	/**
	 * @return the associate
	 */
	public MAssociate getAssociate() {
		return associate;
	}
	/**
	 * @param associate the associate to set
	 */
	public void setAssociate(MAssociate associate) {
		this.associate = associate;
	}
	/**
	 * @return the minimumBillableHours
	 */
	public Double getMinimumBillableHours() {
		return minimumBillableHours;
	}
	/**
	 * @param minimumBillableHours the minimumBillableHours to set
	 */
	public void setMinimumBillableHours(Double minimumBillableHours) {
		this.minimumBillableHours = minimumBillableHours;
	}
	/**
	 * @return the fgClarityDeviation
	 */
	public Double getFgClarityDeviation() {
		return fgClarityDeviation;
	}
	/**
	 * @param fgClarityDeviation the fgClarityDeviation to set
	 */
	public void setFgClarityDeviation(Double fgClarityDeviation) {
		this.fgClarityDeviation = fgClarityDeviation;
	}
	/**
	 * @return the fgMinBillableDeviation
	 */
	public Double getFgMinBillableDeviation() {
		return fgMinBillableDeviation;
	}
	/**
	 * @param fgMinBillableDeviation the fgMinBillableDeviation to set
	 */
	public void setFgMinBillableDeviation(Double fgMinBillableDeviation) {
		this.fgMinBillableDeviation = fgMinBillableDeviation;
	}
	/**
	 * @return the fgBillableHours
	 */
	public Double getFgBillableHours() {
		return fgBillableHours;
	}
	/**
	 * @param fgBillableHours the fgBillableHours to set
	 */
	public void setFgBillableHours(Double fgBillableHours) {
		this.fgBillableHours = fgBillableHours;
	}
	/**
	 * @return the clarityBillableHours
	 */
	public Double getClarityBillableHours() {
		return clarityBillableHours;
	}
	/**
	 * @param clarityBillableHours the clarityBillableHours to set
	 */
	public void setClarityBillableHours(Double clarityBillableHours) {
		this.clarityBillableHours = clarityBillableHours;
	}
	/**
	 * @return the clarityList
	 */
	public List<MClarity> getClarityList() {
		return clarityList;
	}
	/**
	 * @param clarityList the clarityList to set
	 */
	public void setClarityList(List<MClarity> clarityList) {
		this.clarityList = clarityList;
	}
	/**
	 * @return the fgList
	 */
	public List<MFieldGlass> getFgList() {
		return fgList;
	}
	/**
	 * @param fgList the fgList to set
	 */
	public void setFgList(List<MFieldGlass> fgList) {
		this.fgList = fgList;
	}
}
