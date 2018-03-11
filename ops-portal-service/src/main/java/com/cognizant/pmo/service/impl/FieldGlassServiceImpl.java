/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.AssociateClarityModel;
import com.cognizant.pmo.bo.AssociateUBR;
import com.cognizant.pmo.bo.Deviations;
import com.cognizant.pmo.bo.HolidayModel;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.entity.mongo.MHoliday;
import com.cognizant.pmo.entity.mongo.MProject;
import com.cognizant.pmo.entity.mongo.MProjectAllocation;
import com.cognizant.pmo.repository.mongo.MAssociateRepository;
import com.cognizant.pmo.repository.mongo.MClarityRepository;
import com.cognizant.pmo.repository.mongo.MFieldGlassRepository;
import com.cognizant.pmo.repository.mongo.MHolidayRepository;
import com.cognizant.pmo.repository.mongo.MProjectRepository;
import com.cognizant.pmo.service.FieldGlassService;
import com.cognizant.pmo.utils.ProcessTimer;
import com.cognizant.pmo.utils.Utils;

/**
 * @author 238209
 *
 */
@Component
public class FieldGlassServiceImpl implements FieldGlassService {

	@Resource
	private MAssociateRepository mAssociateRepository;
	
	@Resource
	private MProjectRepository mProjectRepository;
	
	@Resource 
	private MClarityRepository mClarityRepository;
	
	@Resource
	private MFieldGlassRepository mFieldGlassRepository;
	
	@Resource
	private MHolidayRepository mHolidayRepository;
	
	private static HolidayModel holidayModel;
	
	@Override
	public List<Deviations> getEsaVsFgDeviations(List<Long> accountIdList, LocalDate startDate, LocalDate endDate) {
		
		loadHolidays(startDate);
		
		List<BigInteger> projectIdList = getProjectsForAccounts(accountIdList);
		
		ProcessTimer.startTimer();

		/*List<BigInteger> associateIdList = new ArrayList<BigInteger>();
		//associateIdList.add(new BigInteger("112244"));
		//associateIdList.add(new BigInteger("120473"));
		associateIdList.add(new BigInteger("162614"));
		associateIdList.add(new BigInteger("118607"));
		
		List<MAssociate> associateList = 
				mAssociateRepository.findByAssociateIdsQuery(associateIdList);*/

		List<MAssociate> associateList = 
				mAssociateRepository.findByProjectIdQuery(projectIdList);
		ProcessTimer.endTimer("Associate Loading");
		
		List<Deviations> deviationsList = new ArrayList<Deviations>();
		
		ProcessTimer.startTimer();
		associateList.forEach(associate -> {
			Deviations deviation = getClarityAndFgDetails(associate, startDate, endDate);
			
			calculateDeviations(deviation, startDate, endDate);
			
			deviationsList.add(deviation);
		});
		ProcessTimer.endTimer("Load Clarity / FG and Deviations");
		
		return deviationsList;
	}

	private void loadHolidays(LocalDate startDate) {
		if (holidayModel == null) {
			int year = startDate.getYear();
			
			List<MHoliday> holidayList = 
					mHolidayRepository.findByGtDateQuery(LocalDate.of(year, Month.JANUARY, 1));
			holidayModel = new HolidayModel(holidayList);
		}
	}

	private Deviations getClarityAndFgDetails(MAssociate associate, 
							LocalDate startDate, LocalDate endDate) {

		Deviations deviation = new Deviations();
		
		//ProcessTimer.startTimer();
		List<MClarity> clarityList = 
				mClarityRepository.findByResourceIdAndStartDateAndEndDateQuery(
										associate.getResourceWorkdayId(), startDate, endDate);
		//ProcessTimer.endTimer("Clarity Load");
		
		deviation.setAssociate(associate);
		deviation.setClarityList(clarityList);
		
		//ProcessTimer.startTimer();
		if (associate.getFgWorkerId() != null
				&& StringUtils.equalsIgnoreCase("No Worker ID", associate.getFgWorkerId()) == false) {
			List<MFieldGlass> fgList = 
					mFieldGlassRepository.findByResourceIdAndStartDateAndEndDateQuery(
							associate.getFgWorkerId(), startDate, endDate);

			deviation.setFgList(fgList);
		}
		//ProcessTimer.endTimer("FG Load");
		
		return deviation;
	}

	private void calculateDeviations(Deviations deviation, LocalDate startDate, LocalDate endDate) {
		//ProcessTimer.startTimer();
		
		AssociateClarityModel clarityModel = new AssociateClarityModel(deviation);
		clarityModel.calculate(startDate, endDate);

		deviation.setClarityBillableHours(clarityModel.getApprovedClarityBillableHours());
		deviation.setClarityUnpaidHours(clarityModel.getApprovedUnpaidTimeoffHours());
		deviation.setFgBillableHours(clarityModel.getApprovedFgBillableHours());

		// Calculate the Minimum Billable Hours
		deviation.calculate(startDate, endDate, holidayModel);
		
		//ProcessTimer.endTimer("Calculate Deviations");
	}

	private List<BigInteger> getProjectsForAccounts(List<Long> accountIdList) {
		List<MProject> projectList = new ArrayList<MProject>();
		
		accountIdList.forEach(accountId -> {
			projectList.addAll(mProjectRepository.findByAccountId(accountId));
		});
		
		List<BigInteger> projectIdList = new ArrayList<BigInteger>();
		projectList.forEach(project -> projectIdList.add(project.getId()));
		
		return projectIdList;
	}

	@Override
	public List<AssociateUBR> getUBRDetails(List<Long> accountIdList, String month) {
		List<BigInteger> projectIdList = getProjectsForAccounts(accountIdList);

		List<MAssociate> associateList = 
				mAssociateRepository.findByProjectIdAndAllocationPercentQuery(projectIdList, 1);
		
		List<AssociateUBR> ubrList = new ArrayList<AssociateUBR>();
		associateList.forEach(a -> {
			List<AssociateUBR> assUbrList = calculateUBR(a, Integer.parseInt(month), projectIdList);
			
			ubrList.addAll(assUbrList);
		});
		
		return ubrList;
	}

	private List<AssociateUBR> calculateUBR(MAssociate a, int month, List<BigInteger> projectIdList) {
		LocalDate monthStartDate = Utils.getMonthStartDate(month);
		
		loadHolidays(monthStartDate);
		
		List<AssociateUBR> ubrList = calculateMinBillableHoursPerWeek(a, monthStartDate, 
																		Utils.getMonthEndDate(month),
																		projectIdList);
		
		return ubrList;
	}
	
	private List<AssociateUBR> calculateMinBillableHoursPerWeek(MAssociate associate, LocalDate startDate, 
															LocalDate endDate, List<BigInteger> projectIdList) {
		List<AssociateUBR> associateUbrList = new ArrayList<AssociateUBR>();
		
		List<MProjectAllocation> allocationsList = associate.getProjectAllocations();
		
		List<LocalDate> weekStartDates = Utils.getWeekStartDates(startDate.getMonthValue());
		
		allocationsList.forEach(allocation -> {
			if (projectIdList.contains(allocation.getProjectId()) && allocation.getAssignmentPercentage() > 1) {
				AssociateUBR associateUbr = new AssociateUBR();
				associateUbr.setAssociate(associate);
				
				weekStartDates.forEach(ws -> {
					LocalDate actualStartDate = Utils.getMaxDate(allocation.getAssignmentStartDate(), ws);
					LocalDate actualEndDate = Utils.getMinDate(allocation.getAssignmentEndDate(), Utils.getWeekEndDate(ws));
					
					double workHours = 0;
					
					if (allocation.isBillable()) {
						workHours = Utils.calculateWorkHours(actualStartDate, actualEndDate,
								associate.getLocation(),
								allocation.getAssignmentPercentage(),
								holidayModel,
								associate.getCity());
					} else {
						double nblHours = Utils.calculateWorkHours(actualStartDate, actualEndDate,
								associate.getLocation(),
								allocation.getAssignmentPercentage(),
								holidayModel,
								associate.getCity());
						associateUbr.setNblHours(nblHours);
					}
					
					int week = ws.get(ChronoField.ALIGNED_WEEK_OF_MONTH) + 1;
					associateUbr.setWeekHours(week, workHours);
					double holidayHours = Utils.calculateHolidayHours(actualStartDate, actualEndDate, holidayModel, 
																		associate.getCity(), associate.getLocation());
					
					associateUbr.setVacationHours(holidayHours);
					associateUbr.setRate(associate.getFgRate());
				});
				
				associateUbrList.add(associateUbr);
			}
		});
	
		return associateUbrList;
	}
}
