/**
 * 
 */
package com.cognizant.pmo.validator;

import org.apache.poi.ss.usermodel.Row;

/**
 * @author 238209
 *
 */
public interface RecordValidator {

	public boolean isValidHeaderRecord(Row row);
	
	public boolean isValidDataRecord(Row row);
	
}
