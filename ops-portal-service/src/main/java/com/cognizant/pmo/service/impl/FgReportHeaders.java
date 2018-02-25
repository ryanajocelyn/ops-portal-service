/**
 * 
 */
package com.cognizant.pmo.service.impl;

/**
 * @author 238209
 *
 */
public enum FgReportHeaders {
	WORKER_ID("Worker ID"),
	WORKER_NAME("Worker"),
	INVOICE_STATUS("Invoice Status"),
	BILLABLE_HOURS("Billable Hours"),
	INVOICE_AMOUNT("Invoice Amount"),
	ONSITE_OFFSHORE("On/Off Shore(ESRX)"),
	START_DATE("Invoice Line Item Start Date"),
	END_DATE("Invoice Line Item End Date"),
	BILL_RATE_ONSITE("Bill Rate [ST/Hr]"),
	BILL_RATE_OFFSHORE("Bill Rate [ST Offshore/Hr]"),
	JOB_POSTING_TITLE("Job Posting Title");
	
	private String headerName;
	
	FgReportHeaders(String headerName) {
		this.headerName = headerName;
	}
	
	public String value() {
		return headerName;
	}
}
