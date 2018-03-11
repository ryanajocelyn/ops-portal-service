/**
 * 
 */
package com.cognizant.pmo.service.impl;

/**
 * @author 238209
 *
 */
public enum ClarityReportHeaders {
	RESOURCE_WORKDAY_ID("Resource Workday ID"),
	RESOURCE_NAME("Resource Name "),
	RESOURCE_MANAGER_NAME("Resource Manager"),
	RM_WORKDAY_ID("Resource Manager WorkDay ID"),
	WEEK_START_DATE("Week Start Date"),
	WEEK_END_DATE("Week End Date"),
	PROJECT_ID("Project ID"),
	PROJECT_TITLE("Project Title"),
	TASK_ID("Task ID"),
	TASK_TITLE("Task Title"),
	HOURS_SUN("SUN"),
	HOURS_MON("MON"),
	HOURS_TUE("TUES"),
	HOURS_WED("WED"),
	HOURS_THU("THU"),
	HOURS_FRI("FRI"),
	HOURS_SAT("SAT"),
	HOURS_TOTAL("Total Hours"),
	RATE("Rate"),
	SUBMITTED_BY("Submitted By"),
	SUBMITTED_DATE("Submitted Timestamp"),
	REJECTED_BY("Rejected By"),
	REJECTED_DATE("Rejected Timestamp"),
	IS_APPROVED("Appr Indicator"),
	APPROVED_BY("Approved By"),
	APPROVED_DATE("Approved Timestamp"),
	HOURS_WEEK("Hours/week");
	
	private String headerName;
	
	ClarityReportHeaders(String headerName) {
		this.headerName = headerName;
	}
	
	public String value() {
		return headerName;
	}
}
