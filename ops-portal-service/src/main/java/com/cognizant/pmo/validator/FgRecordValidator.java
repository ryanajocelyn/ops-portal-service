/**
 * 
 */
package com.cognizant.pmo.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.cognizant.pmo.utils.ExcelFileReader;

/**
 * @author 238209
 *
 */
public class FgRecordValidator implements RecordValidator {

	/* (non-Javadoc)
	 * @see com.cognizant.pmo.validator.RecordValidator#isValidHeaderRecord(org.apache.poi.ss.usermodel.Row)
	 */
	@Override
	public boolean isValidHeaderRecord(Row row) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.pmo.validator.RecordValidator#isValidDataRecord(org.apache.poi.ss.usermodel.Row)
	 */
	@Override
	public boolean isValidDataRecord(Row row) {
		boolean isValidDataRow = false;
		if (row != null) {
			Cell cell = row.getCell(0);
			if (cell != null) {
				String cellData = ExcelFileReader.getCellData(cell);
				if (StringUtils.startsWith(cellData, "ESRX")) {
					isValidDataRow = true;
				}
			}
		}
		
		return isValidDataRow;
	}

}
