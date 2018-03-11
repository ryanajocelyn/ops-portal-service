/**
 * 
 */
package com.cognizant.pmo.bo;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cognizant.pmo.entity.mongo.MHoliday;

/**
 * @author 238209
 *
 */
public class HolidayModel {

	private List<MHoliday> holidayList;
	
	public HolidayModel(List<MHoliday> holidayList) {
		this.holidayList = holidayList;
	}

	public boolean isWorkDay(LocalDate currDate, String city) {
		for (MHoliday mHoliday : holidayList) {
			
			String associateCity = getCityCode(city);
			String holidayCity = StringUtils.lowerCase(mHoliday.getLocation());
			
			if (StringUtils.contains(holidayCity, associateCity) 
					&& currDate.isEqual(mHoliday.getDate())) {
				return false;
			}
		}
		
		return true;
	}

	private String getCityCode(String city) {
		String code = null;
		city = StringUtils.lowerCase(city);
		
		switch (city) {
		case "chennai":
			code = "c";
			break;
			
		case "kolkata":
			code="k";
			break;
			
		case "hyderabad":
			code="h";
			break;
			
		case "bangalore":
			code="b";
			break;
			
		default:
			code="o";
			break;
		}
		
		return code;
	}

	/**
	 * @return the holidayList
	 */
	public List<MHoliday> getHolidayList() {
		return holidayList;
	}

	/**
	 * @param holidayList the holidayList to set
	 */
	public void setHolidayList(List<MHoliday> holidayList) {
		this.holidayList = holidayList;
	}
}
