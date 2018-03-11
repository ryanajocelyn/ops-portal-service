/**
 * 
 */
package com.cognizant.pmo.service.impl;

/**
 * @author 238209
 *
 */
public enum FcReportHeaders {
	ASSOCIATE_ID("Associate ID"), 
	ASSOCIATE_NAME("Associate Name"), 
	BGV("BGV"), 
	DOJ("Date of Joining"), 
	CITY("City"), 
	COUNTRY("Country"), 
	LOCATION("Onsite/Offshore"), 
	LOCATION_ID("Location ID"), 
	STATE("State"), 
	GRADE("Grade HR"), 
	DEPARTMENT_NAME("Department Name"), 
	FG_WORKER_ID("FG Worker ID"), 
	FG_TERMINATION_DATE("FG Worker End Date"), 
	DESIGNATION("Designation"), 
	VERTICAL_HORIZONTAL("Vertical/Horizontal"), 
	ASSIGNMENT_ID("Assignment ID"),
	ASSIGNMENT_START_DATE("Assignment Start Date"),
	ASSIGNMENT_END_DATE("Assignment End Date"),
	ASSIGNMENT_STATUS("Assignment Status"),
	ASSIGNMENT_PERCENTAGE("Percent Allocation"), 
	PROJECT_ID("Project ID"), 
	BILLABILITY_STATUS("Billability Status");
	
	private String headerName;
	
	FcReportHeaders(String headerName) {
		this.headerName = headerName;
	}
	
	public String value() {
		return headerName;
	}
}
