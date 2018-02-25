/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.Record;
import com.cognizant.pmo.entity.mongo.MAssociate;
import com.cognizant.pmo.entity.mongo.MClarity;
import com.cognizant.pmo.entity.mongo.MFieldGlass;
import com.cognizant.pmo.entity.mongo.MProjectAllocation;
import com.cognizant.pmo.repository.mongo.MAssociateRepository;
import com.cognizant.pmo.repository.mongo.MClarityRepository;
import com.cognizant.pmo.repository.mongo.MFieldGlassRepository;
import com.cognizant.pmo.service.FileProcessService;
import com.cognizant.pmo.utils.ExcelFileReader;
import com.cognizant.pmo.validator.FgRecordValidator;

/**
 * @author 238209
 *
 */
@Component
public class FileProcessServiceImpl implements FileProcessService {

	private static Logger logger = Logger.getLogger(FileProcessServiceImpl.class);
	
	@Resource
	private MAssociateRepository mAssociateRepository;

	@Resource
	private MClarityRepository mClarityRepository;

	@Resource
	private MFieldGlassRepository mFgRepository;

	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.FileProcessService#readAndLoadFcReport(java.lang.String)
	 */
	@Override
	public boolean readAndLoadFcReport(String fileName) {
		List<Record> fcReportList = ExcelFileReader.readFileWithHeader(fileName, "BaseP");

		logger.debug("FC Assignment Report: Count from file = " + fcReportList.size());
		System.out.println("FC Assignment Report: Count from file = " + fcReportList.size());
		
		MAssociate associate = null;
		for (Record record : fcReportList) {
			associate = searchAndFindOneAssociate(record);

			associate.setId(record.getBigIntValue(FcReportHeaders.ASSOCIATE_ID.value()));
			associate.setAssociateName(record.getStringValue(FcReportHeaders.ASSOCIATE_NAME.value()));
			associate.setBgvStatus(record.getStringValue(FcReportHeaders.BGV.value()));
			associate.setDateOfJoining(record.getDateTimeValue(FcReportHeaders.DOJ.value()));
			associate.setCity(record.getStringValue(FcReportHeaders.CITY.value()));
			associate.setCountry(record.getStringValue(FcReportHeaders.COUNTRY.value()));
			associate.setLocation(record.getStringValue(FcReportHeaders.LOCATION.value()));
			associate.setLocationId(record.getStringValue(FcReportHeaders.LOCATION_ID.value()));
			associate.setState(record.getStringValue(FcReportHeaders.STATE.value()));
			associate.setFgWorkerId(record.getStringValue(FcReportHeaders.FG_WORKER_ID.value()));
			associate.setFgTerminationDate(record.getDateTimeValue(FcReportHeaders.FG_TERMINATION_DATE.value()));

			associate.setGrade(record.getStringValue(FcReportHeaders.GRADE.value()));
			associate.setGradeDescription(record.getStringValue(FcReportHeaders.DESIGNATION.value()));

			associate.setDepartment(record.getStringValue(FcReportHeaders.DEPARTMENT_NAME.value()));
			associate.setDepartmentType(record.getStringValue(FcReportHeaders.VERTICAL_HORIZONTAL.value()));

			List<MProjectAllocation> projAllocations = associate.getProjectAllocations();
			MProjectAllocation allocation = null;
			BigInteger assignmentId = record.getBigIntValue(FcReportHeaders.ASSIGNMENT_ID.value());

			for (MProjectAllocation mProjectAllocation : projAllocations) {
				if (assignmentId.equals(mProjectAllocation.getAssignmentId())) {
					allocation = mProjectAllocation;
					break;
				}
			}

			if (allocation == null) {
				allocation = new MProjectAllocation();
				projAllocations.add(allocation);
			}

			allocation.setAssignmentId(assignmentId);
			allocation.setAssignmentStartDate(record.getDateTimeValue(FcReportHeaders.ASSIGNMENT_START_DATE.value()));
			allocation.setAssignmentEndDate(record.getDateTimeValue(FcReportHeaders.ASSIGNMENT_END_DATE.value()));
			allocation.setAssignmentStatus(record.getStringValue(FcReportHeaders.ASSIGNMENT_STATUS.value()));
			allocation.setAssignmentPercentage(record.getIntValue(FcReportHeaders.ASSIGNMENT_PERCENTAGE.value()));
			allocation.setProjectId(record.getBigIntValue(FcReportHeaders.PROJECT_ID.value()));
			
			mAssociateRepository.save(associate);
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.FileProcessService#readAndLoadClarityReport()
	 */
	@Override
	public boolean readAndLoadClarityReport(String fileName) {
		List<Record> fcReportList = ExcelFileReader.readFileWithHeader(fileName, 3);

		MClarity clarity = null;
		for (Record record : fcReportList) {
			clarity = searchAndFindOneClarity(record);

			clarity.setResourceWorkdayId(record.getLongValue(ClarityReportHeaders.RESOURCE_WORKDAY_ID.value()));
			clarity.setAssociateName(record.getStringValue(ClarityReportHeaders.RESOURCE_NAME.value()));
			clarity.setResourceManagerName(record.getStringValue(ClarityReportHeaders.RESOURCE_MANAGER_NAME.value()));
			clarity.setRmWorkdayId(record.getLongValue(ClarityReportHeaders.RM_WORKDAY_ID.value()));
			clarity.setWeekStartDate(record.getDateTimeValue(ClarityReportHeaders.WEEK_START_DATE.value()));
			clarity.setWeekEndDate(record.getDateTimeValue(ClarityReportHeaders.WEEK_END_DATE.value()));
			clarity.setEsiProjectId(record.getStringValue(ClarityReportHeaders.PROJECT_ID.value()));
			clarity.setEsiProjectTitle(record.getStringValue(ClarityReportHeaders.PROJECT_TITLE.value()));
			clarity.setEsiTaskId(record.getStringValue(ClarityReportHeaders.TASK_ID.value()));
			clarity.setEsiTaskTitle(record.getStringValue(ClarityReportHeaders.TASK_TITLE.value()));
			clarity.setHoursSun(record.getDoubleValue(ClarityReportHeaders.HOURS_SUN.value()));
			clarity.setHoursMon(record.getDoubleValue(ClarityReportHeaders.HOURS_MON.value()));
			clarity.setHoursTue(record.getDoubleValue(ClarityReportHeaders.HOURS_TUE.value()));
			clarity.setHoursWed(record.getDoubleValue(ClarityReportHeaders.HOURS_WED.value()));
			clarity.setHoursThu(record.getDoubleValue(ClarityReportHeaders.HOURS_THU.value()));
			clarity.setHoursFri(record.getDoubleValue(ClarityReportHeaders.HOURS_FRI.value()));
			clarity.setHoursSat(record.getDoubleValue(ClarityReportHeaders.HOURS_SAT.value()));
			clarity.setWeekTotal(record.getDoubleValue(ClarityReportHeaders.HOURS_TOTAL.value()));
			clarity.setRate(record.getDoubleValue(ClarityReportHeaders.RATE.value()));
			clarity.setSubmiitedBy(record.getStringValue(ClarityReportHeaders.SUBMITTED_BY.value()));
			clarity.setSubmittedOn(record.getDateTimeValue(ClarityReportHeaders.SUBMITTED_DATE.value()));
			clarity.setRejectedBy(record.getStringValue(ClarityReportHeaders.REJECTED_BY.value()));
			clarity.setRejectedOn(record.getDateTimeValue(ClarityReportHeaders.REJECTED_DATE.value()));


			String isApprovedFlag = record.getStringValue(ClarityReportHeaders.IS_APPROVED.value());
			clarity.setApproved("1".equalsIgnoreCase(isApprovedFlag)?true:false);

			clarity.setApprovedBy(record.getStringValue(ClarityReportHeaders.APPROVED_BY.value()));
			clarity.setApprovedOn(record.getDateTimeValue(ClarityReportHeaders.APPROVED_DATE.value()));

			clarity.setMaxWeekHours(record.getDoubleValue(ClarityReportHeaders.HOURS_WEEK.value()));

			mClarityRepository.save(clarity);
		}

		return false;
	}

	private MClarity searchAndFindOneClarity(Record record) {
		Long resWorkdayId = record.getLongValue(ClarityReportHeaders.RESOURCE_WORKDAY_ID.value());
		String taskId = record.getStringValue(ClarityReportHeaders.TASK_ID.value());
		Date weekStartDate = record.getDateTimeValue(ClarityReportHeaders.WEEK_START_DATE.value());

		List<MClarity> clarityList = mClarityRepository.findByResourceIdAndTaskIdAndStartDateQuery(resWorkdayId, taskId, weekStartDate);

		MClarity clarity = new MClarity();
		if (!clarityList.isEmpty()) {
			clarity = clarityList.get(0);
		} 
		return clarity;
	}

	private MAssociate searchAndFindOneAssociate(Record record) {
		BigInteger associateId = record.getBigIntValue(FcReportHeaders.ASSOCIATE_ID.value());

		MAssociate associate = mAssociateRepository.findOne(associateId);
		if (associate == null || associate.getAssociateId() == null || associate.getAssociateId().intValue() == 0) {
			associate = new MAssociate();
		}

		return associate;
	}

	@Override
	public boolean readAndLoadFGReport(String fileName) {
		List<Record> fgReportList = ExcelFileReader.readFileWithHeader(fileName, 1, 
				new FgRecordValidator());

		MFieldGlass fg = null;
		for (Record record : fgReportList) {
			fg = searchAndFindOneFG(record);

			fg.setFgWorkerId(record.getStringValue(FgReportHeaders.WORKER_ID.value()));
			fg.setFgWorkerName(record.getStringValue(FgReportHeaders.WORKER_NAME.value()));
			fg.setInvoiceStatus(record.getStringValue(FgReportHeaders.INVOICE_STATUS.value()));
			fg.setWorkerLocation(record.getStringValue(FgReportHeaders.ONSITE_OFFSHORE.value()));
			fg.setBillableHours(record.getDoubleValue(FgReportHeaders.BILLABLE_HOURS.value()));
			fg.setFgStartDate(record.getDateValue(FgReportHeaders.START_DATE.value()));
			fg.setFgEndDate(record.getDateValue(FgReportHeaders.END_DATE.value()));
			fg.setFgJobPostingTitle(record.getStringValue(FgReportHeaders.JOB_POSTING_TITLE.value()));

			if ("Onshore".equalsIgnoreCase(fg.getWorkerLocation())) {
				fg.setFgRate(record.getDoubleValue(FgReportHeaders.BILL_RATE_ONSITE.value()));
			} else {
				fg.setFgRate(record.getDoubleValue(FgReportHeaders.BILL_RATE_OFFSHORE.value()));
			}

			fg.setInvoiceAmount(record.getDoubleValue(FgReportHeaders.INVOICE_AMOUNT.value()));

			mFgRepository.save(fg);
		}

		return false;
	}

	private MFieldGlass searchAndFindOneFG(Record record) {
		String fgWorkerId = record.getStringValue(FgReportHeaders.WORKER_ID.value());
		Date fgStartDate = record.getDateValue(FgReportHeaders.START_DATE.value());

		MFieldGlass fg = new MFieldGlass();
		List<MFieldGlass> fgList = mFgRepository.findByResourceIdAndStartDateQuery(fgWorkerId, fgStartDate);
		if (fgList != null && !fgList.isEmpty()) {
			fg = fgList.get(0);
		}

		return fg;
	}

	@Override
	public boolean readAndLoadXRefReport(String fileName) {

		List<Record> fcReportList = ExcelFileReader.readFileWithHeader(fileName);

		MAssociate associate = null;
		for (Record record : fcReportList) {
			associate = searchAndFindOneAssociate(record);
			
			if (associate.getAssociateId() != null && associate.getAssociateId().longValue() > 0) {
				associate.setResourceWorkdayId(record.getLongValue(ClarityReportHeaders.RESOURCE_WORKDAY_ID.value()));
				
				mAssociateRepository.save(associate);
			}
		}

		return false;
	}
}
