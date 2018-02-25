/**
 * 
 */
package com.cognizant.pmo.service;

/**
 * @author 238209
 *
 */
public interface FileProcessService {

	public boolean readAndLoadFcReport(String fileName);

	public boolean readAndLoadClarityReport(String fileName);

	public boolean readAndLoadFGReport(String fileName);
	
	public boolean readAndLoadXRefReport(String fileName);
}
