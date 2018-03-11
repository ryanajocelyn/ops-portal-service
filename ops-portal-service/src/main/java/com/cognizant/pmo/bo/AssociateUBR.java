/**
 * 
 */
package com.cognizant.pmo.bo;

import java.math.BigInteger;

import com.cognizant.pmo.entity.mongo.MAssociate;

/**
 * @author 238209
 *
 */
public class AssociateUBR {

	private MAssociate associate;
	private double week3;
	private double ubr3;
	private double week4;
	private double ubr4;
	private double week5;
	private double ubr5;

	private double nblHours;
	private double vacationHours;

	private double rate;
	private double revenue;

	private BigInteger id;
	private String associateName;

	public void setWeekHours(int week, double workHours) {
		switch (week) {
		case 3:
			setWeek3(workHours);
			break;
		case 4:
			setWeek4(workHours);
			break;
		case 5:
			setWeek5(workHours);
			break;
		default:
			break;
		}
	}

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
		
		setId(associate.getId());
		setAssociateName(associate.getAssociateName());
	}
	/**
	 * @return the week3
	 */
	public double getWeek3() {
		return week3;
	}
	/**
	 * @param week3 the week3 to set
	 */
	public void setWeek3(double week3) {
		this.week3 = week3;
	}
	/**
	 * @return the ubr3
	 */
	public double getUbr3() {
		return ubr3;
	}
	/**
	 * @param ubr3 the ubr3 to set
	 */
	public void setUbr3(double ubr3) {
		this.ubr3 = ubr3;
	}
	/**
	 * @return the week4
	 */
	public double getWeek4() {
		return week4;
	}
	/**
	 * @param week4 the week4 to set
	 */
	public void setWeek4(double week4) {
		this.week4 = week4;
	}
	/**
	 * @return the ubr4
	 */
	public double getUbr4() {
		return ubr4;
	}
	/**
	 * @param ubr4 the ubr4 to set
	 */
	public void setUbr4(double ubr4) {
		this.ubr4 = ubr4;
	}
	/**
	 * @return the week5
	 */
	public double getWeek5() {
		return week5;
	}
	/**
	 * @param week5 the week5 to set
	 */
	public void setWeek5(double week5) {
		this.week5 = week5;
	}
	/**
	 * @return the ubr5
	 */
	public double getUbr5() {
		return ubr5;
	}
	/**
	 * @param ubr5 the ubr5 to set
	 */
	public void setUbr5(double ubr5) {
		this.ubr5 = ubr5;
	}
	/**
	 * @return the nblHours
	 */
	public double getNblHours() {
		return nblHours;
	}
	/**
	 * @param nblHours the nblHours to set
	 */
	public void setNblHours(double nblHours) {
		this.nblHours = nblHours;
	}
	/**
	 * @return the vacationHours
	 */
	public double getVacationHours() {
		return vacationHours;
	}
	/**
	 * @param vacationHours the vacationHours to set
	 */
	public void setVacationHours(double vacationHours) {
		this.vacationHours = vacationHours;
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
	 * @return the revenue
	 */
	public double getRevenue() {
		return revenue;
	}
	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(double revenue) {
		this.revenue = revenue;
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
}
