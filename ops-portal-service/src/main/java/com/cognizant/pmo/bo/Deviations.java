/**
 * 
 */
package com.cognizant.pmo.bo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.entity.mongo.MProjectAllocation;
import com.cognizant.pmo.utils.Constants;
import com.cognizant.pmo.utils.LocalDateComparator;
import com.cognizant.pmo.utils.Utils;

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
	private Double clarityUnpaidHours;
	
	private BigInteger associateId;
	private String associateName;

	private List<MClarity> clarityList;
	private List<MFieldGlass> fgList;
	private Map<BigInteger, Double> minBillableHoursByProject;
	
	private List<DeviationDetail> deviationDetails;
	
	private int deviationReasonCode;
	private String deviationReason;
	
	private boolean hasDeviation;
	
	private double holidayHours;
	
	public void calculate(LocalDate startDate, LocalDate endDate, HolidayModel holidayModel) {
		minBillableHoursByProject = new HashMap<BigInteger, Double>();
		minimumBillableHours = 0.0;
		
		calculateMinBillableHours(startDate, endDate, holidayModel);

		setFgClarityDeviation(fgBillableHours - clarityBillableHours);
		
		double fgMinBillDeviation = fgBillableHours - minimumBillableHours;
		if ((clarityUnpaidHours - holidayHours) > 0) {
			fgMinBillDeviation += (clarityUnpaidHours - holidayHours); 
		}
		
		setFgMinBillableDeviation(fgMinBillDeviation); 
		setHasDeviation((fgClarityDeviation < 0 || fgMinBillableDeviation < 0) ? true : false);
		
		associateId = associate.getId();
		associateName = associate.getAssociateName();
		
		calculateDeviationDetails(startDate, endDate);
		
		identifyReasons();
	}
	
	private void calculateDeviationDetails(LocalDate startDate, LocalDate endDate) {
		Map<LocalDate, DeviationDetail> deviationByWeek = new HashMap<LocalDate, DeviationDetail>();
		
		populateDeviationByWeek(startDate, endDate, deviationByWeek);
	
		populateClarityDeviationDetails(deviationByWeek);
		
		if (fgList != null) {
			for (MFieldGlass mFieldGlass : fgList) {
				DeviationDetail detail = getDeviationDetail(deviationByWeek, mFieldGlass.getFgStartDate());

				detail.addFieldGlassBillableHours(mFieldGlass.getBillableHours());
			}
		}
		
		deviationDetails = new ArrayList<DeviationDetail>(deviationByWeek.values());
		deviationDetails.sort(new LocalDateComparator());
	}

	private void identifyReasons() {
		if (fgClarityDeviation == 0 && fgMinBillableDeviation == 0) {
			deviationReason = "No Deviation";
			deviationReasonCode = 0;
		} else {
			if (fgClarityDeviation < 0) {
				if (hasAnyUnapprovedClarity()) {
					deviationReason = "Clarity Not Approved";
					deviationReasonCode = 2;
				} else {
					deviationReason = "No FG Hours";
					deviationReasonCode = 3;
				}
			} else if (fgMinBillableDeviation < 0) {
				if (isAssociateNBL()) {
					deviationReason = "NBL";
					deviationReasonCode = 4;
				} else {
					deviationReason = "No Clarity / FG Hours";
					deviationReasonCode = 1;
				}
			}
		}
	}

	private boolean isAssociateNBL() {
		List<MProjectAllocation> nblAssociates = 
				associate.getProjectAllocations().stream()
							.filter(c -> {
								if (c.isBillable() == false && c.getAssignmentPercentage() > 1) {
									return true;
								}
								
								return false;
							})
							.collect(Collectors.toList());

		return nblAssociates.isEmpty() == false;
	}

	private boolean hasAnyUnapprovedClarity() {
		List<MClarity> unApprovedClarity = 
				clarityList.stream().filter(c -> !c.isApproved())
							.collect(Collectors.toList());
		return unApprovedClarity.isEmpty() == false;
	}

	/**
	 * @param deviationByWeek
	 */
	private void populateClarityDeviationDetails(Map<LocalDate, DeviationDetail> deviationByWeek) {
		LocalDate weekStart = null;
		for (MClarity mClarity : clarityList) {
			weekStart = Utils.getPreviousWeekStartDate(mClarity.getWeekStartDate());
			System.out.println("Week Start: " + mClarity.getWeekStartDate() + " :  new date: " + weekStart);
			DeviationDetail detail = getDeviationDetail(deviationByWeek, weekStart);
			
			if (StringUtils.contains(mClarity.getEsiProjectTitle(), Constants.UNPAID_TEXT)) {
				detail.setClarityNonBillable(detail.getClarityNonBillable() 
												+ AssociateClarityModel.getTotalHoursPerWeek(mClarity));
			} else { 
				detail.setHoursSun(detail.getHoursSun() + mClarity.getHoursSun());
				detail.setHoursMon(detail.getHoursMon() + mClarity.getHoursMon());
				detail.setHoursTue(detail.getHoursTue() + mClarity.getHoursTue());
				detail.setHoursWed(detail.getHoursWed() + mClarity.getHoursWed());
				detail.setHoursThu(detail.getHoursThu() + mClarity.getHoursThu());
				detail.setHoursFri(detail.getHoursFri() + mClarity.getHoursFri());
				detail.setHoursSat(detail.getHoursSat() + mClarity.getHoursSat());

				if (mClarity.isApproved() == false) {
					detail.setClarityUnapproved(
							detail.getClarityUnapproved() + 
							AssociateClarityModel.getTotalHoursPerWeek(mClarity));
				}
			}

			detail.addDeviationSubDetail(mClarity);
			
			detail.setClarityApproved(mClarity.isApproved());
			detail.calculateTotalBillableHours();
		}
	}

	/**
	 * @param startDate
	 * @param endDate
	 * @param deviationByWeek
	 * @return
	 */
	private LocalDate populateDeviationByWeek(LocalDate startDate, LocalDate endDate,
			Map<LocalDate, DeviationDetail> deviationByWeek) {
		LocalDate currWeekStart = LocalDate.from(startDate);
		
		do {
			getDeviationDetail(deviationByWeek, currWeekStart);
			currWeekStart = Utils.getNextWeekStartDate(currWeekStart, false); 
		} while (currWeekStart.isBefore(endDate));
		return currWeekStart;
	}

	/**
	 * @param deviationByWeek
	 * @param currWeekStart
	 * @return
	 */
	private DeviationDetail getDeviationDetail(Map<LocalDate, DeviationDetail> deviationByWeek,
			LocalDate currWeekStart) {
		DeviationDetail detail = deviationByWeek.get(currWeekStart);
		if (detail == null) {
			detail = new DeviationDetail();
			detail.setWeekStartDate(currWeekStart);
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			detail.setWeekStartDateStr(df.format(currWeekStart));
			
			if (associate.getFgTerminationDate() == null) {
				detail.setFgTerminationDate("No End Date");
			} else {
				detail.setFgTerminationDate(df.format(associate.getFgTerminationDate()));
			}
			
			deviationByWeek.put(currWeekStart, detail);
		}
		return detail;
	}

	private void calculateMinBillableHours(LocalDate startDate, LocalDate endDate, HolidayModel holidayModel) {
		List<MProjectAllocation> allocationsList = associate.getProjectAllocations();
		
		allocationsList.forEach(allocation -> {
			LocalDate actualStartDate = Utils.getMaxDate(allocation.getAssignmentStartDate(), startDate);
			LocalDate actualEndDate = Utils.getMinDate(allocation.getAssignmentEndDate(), endDate);
			
			double workHours = 0;
			
			if (allocation.isBillable() && allocation.getAssignmentPercentage() > 1) {
				workHours = Utils.calculateWorkHours(actualStartDate, actualEndDate,
														associate.getLocation(),
														allocation.getAssignmentPercentage(),
														holidayModel,
														associate.getCity());
			}

			minBillableHoursByProject.put(allocation.getProjectId(), workHours);
			minimumBillableHours += workHours;
			
			if (allocation.getAssignmentPercentage() > 1) {
				holidayHours = Utils.calculateHolidayHours(actualStartDate, actualEndDate, holidayModel, 
						associate.getCity(), associate.getLocation());
			}
		});
		
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
	}
	/**
	 * @return the minimumBillableHours
	 */
	public Double getMinimumBillableHours() {
		
		/*minBillableHoursByProject.forEach((k, v) -> {
			minimumBillableHours += v;
		});*/
		
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
	/**
	 * @return the clarityUnpaidHours
	 */
	public Double getClarityUnpaidHours() {
		return clarityUnpaidHours;
	}
	/**
	 * @param clarityUnpaidHours the clarityUnpaidHours to set
	 */
	public void setClarityUnpaidHours(Double clarityUnpaidHours) {
		this.clarityUnpaidHours = clarityUnpaidHours;
	}

	/**
	 * @return the associateId
	 */
	public BigInteger getAssociateId() {
		return associateId;
	}

	/**
	 * @param associateId the associateId to set
	 */
	public void setAssociateId(BigInteger associateId) {
		this.associateId = associateId;
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
	 * @return the deviationDetails
	 */
	public List<DeviationDetail> getDeviationDetails() {
		return deviationDetails;
	}

	/**
	 * @param deviationDetails the deviationDetails to set
	 */
	public void setDeviationDetails(List<DeviationDetail> deviationDetails) {
		this.deviationDetails = deviationDetails;
	}

	/**
	 * @return the deviationReasonCode
	 */
	public int getDeviationReasonCode() {
		return deviationReasonCode;
	}

	/**
	 * @param deviationReasonCode the deviationReasonCode to set
	 */
	public void setDeviationReasonCode(int deviationReasonCode) {
		this.deviationReasonCode = deviationReasonCode;
	}

	/**
	 * @return the deviationReason
	 */
	public String getDeviationReason() {
		return deviationReason;
	}

	/**
	 * @param deviationReason the deviationReason to set
	 */
	public void setDeviationReason(String deviationReason) {
		this.deviationReason = deviationReason;
	}

	/**
	 * @return the hasDeviation
	 */
	public boolean isHasDeviation() {
		return hasDeviation;
	}

	/**
	 * @param hasDeviation the hasDeviation to set
	 */
	public void setHasDeviation(boolean hasDeviation) {
		this.hasDeviation = hasDeviation;
	}

	/**
	 * @return the holidayHours
	 */
	public double getHolidayHours() {
		return holidayHours;
	}

	/**
	 * @param holidayHours the holidayHours to set
	 */
	public void setHolidayHours(double holidayHours) {
		this.holidayHours = holidayHours;
	}
}
