/**
 * 
 */
package com.cognizant.pmo.utils;

import java.util.Comparator;

import com.cognizant.pmo.bo.DeviationDetail;

/**
 * @author 238209
 *
 */
public class LocalDateComparator implements Comparator<DeviationDetail> {

	@Override
	public int compare(DeviationDetail dd1, DeviationDetail dd2) {
		if (dd1.getWeekStartDate() != null && dd2.getWeekStartDate() != null) {
			if (dd1.getWeekStartDate().isBefore(dd2.getWeekStartDate())) {
				return -1;
			} else if (dd1.getWeekStartDate().isAfter(dd2.getWeekStartDate())) {
				return 1;
			} else {
				return 0;
			}
		}
		
		return -1;
	}
}
