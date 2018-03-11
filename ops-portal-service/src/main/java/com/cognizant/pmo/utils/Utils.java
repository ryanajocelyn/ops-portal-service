/**
 * 
 */
package com.cognizant.pmo.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cognizant.pmo.bo.HolidayModel;

/**
 * @author 238209
 *
 */
public class Utils {

	public static String FORMAT_MM_DD_YYYY="MM/dd/yyyy";
	public static String FORMAT_YYYY_MM_DD="yyyy-MM-dd";
	public static String FORMAT_DD_MON_YYYY="dd-MMM-yyyy";
	
	public static boolean isDateBetween(LocalDate date, LocalDate weekStartDate, LocalDate weekEndDate) {
		return (date.isEqual(weekStartDate)) 
					|| (date.isAfter(weekStartDate) && date.isBefore(weekEndDate));
	}
	
	public static LocalDate getMaxDate(LocalDate date1, LocalDate date2) {
		return (date1.isAfter(date2))? date1 : date2;
	}
	
	public static LocalDate getMinDate(LocalDate date1, LocalDate date2) {
		return (date1.isBefore(date2))? date1 : date2;
	}
	
	public static int calculateWorkDays(Date startDate, Date endDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);

		int workDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(endDate);
			endCal.setTime(startDate);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workDays++;
			}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return workDays;
	}
	
	public static long calculateWorkDays(LocalDate startDate, LocalDate endDate, 
											HolidayModel holidayModel, String city) {
		LocalDate currDate = LocalDate.from(startDate);
		
		long workDays = 0;
		do {
			
			if (isWorkDay(currDate) && holidayModel.isWorkDay(currDate, city)) {
				workDays++;
			}
			
			currDate = currDate.plusDays(1);
		} while (currDate.isBefore(endDate.plusDays(1)));
		
		System.out.println(workDays);
		return workDays;
	}
	
	public static double calculateHolidayHours(LocalDate startDate, LocalDate endDate, 
			HolidayModel holidayModel, String city, String location) {
		long holidays = calculateHolidays(startDate, endDate, holidayModel, city);
		
		double workHours = 0;
		
		if (StringUtils.equalsIgnoreCase(location, Constants.ONSITE)) {
			workHours = holidays * 8.0;
		} else { 
			workHours = holidays * 9.0;
		}

		return workHours;
	}
	
	public static long calculateHolidays(LocalDate startDate, LocalDate endDate, 
			HolidayModel holidayModel, String city) {
		LocalDate currDate = LocalDate.from(startDate);

		long holidays = 0;
		do {

			if (holidayModel.isWorkDay(currDate, city) == false) {
				holidays++;
			}

			currDate = currDate.plusDays(1);
		} while (currDate.isBefore(endDate.plusDays(1)));

		System.out.println(holidays);
		return holidays;
	}

	/**
	 * @param currDate
	 * @return
	 */
	private static boolean isWorkDay(LocalDate currDate) {
		boolean isWeekEnd = (currDate.getDayOfWeek() == DayOfWeek.SATURDAY 
									|| currDate.getDayOfWeek() == DayOfWeek.SUNDAY);
		
		return (isWeekEnd == false);
	}
	
	public static void main(String[] args) {
		LocalDate startDate = LocalDate.of(2018, Month.MARCH, 18);
		//LocalDate endDate = LocalDate.of(2018, Month.FEBRUARY, 28);
		
		//calculateWorkDays(startDate, endDate);
		
		//LocalDate tmp = getPreviousWeekStartDate(startDate);
		getWeekStartDates(2).forEach(ws -> {
			
			System.out.println(ws.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
		});
	}
	
	public static double calculateWorkHours(LocalDate startDate, LocalDate endDate, String location, 
											int allocationPercent, HolidayModel holidayModel,
											String city) {
		
		long workDays = calculateWorkDays(startDate, endDate, holidayModel, city);
		double workHours = 0;
		
		if (StringUtils.equalsIgnoreCase(location, Constants.ONSITE)) {
			workHours = workDays * 8.0 * (allocationPercent/100);
		} else { 
			workHours = workDays * 9.0 * (allocationPercent/100);
		}

		return workHours;
	}
	
	public static LocalDate getNextWeekStartDate(LocalDate weekStartDate) {
		return getNextWeekStartDate(weekStartDate, true);
	}
	
	public static LocalDate getNextWeekStartDate(LocalDate weekStartDate, boolean breakByMonthStart) {
		LocalDate nextWeekStart = LocalDate.from(weekStartDate);

		do {
			nextWeekStart = nextWeekStart.plusDays(1);
			if (breakByMonthStart && nextWeekStart.getDayOfMonth() == 1) {
				break;
			}
		} while (DayOfWeek.SUNDAY.equals(nextWeekStart.getDayOfWeek()) == false);
		
		return nextWeekStart;
	}
	
	public static LocalDate getPreviousWeekStartDate(LocalDate weekStartDate) {
		LocalDate nextWeekStart = LocalDate.from(weekStartDate);

		if (DayOfWeek.SUNDAY.equals(nextWeekStart.getDayOfWeek())) {
			return nextWeekStart;
		}
		
		do {
			nextWeekStart = nextWeekStart.minusDays(1);
			if (nextWeekStart.getDayOfMonth() == 1) {
				break;
			}
		} while (DayOfWeek.SUNDAY.equals(nextWeekStart.getDayOfWeek()) == false);
		
		return nextWeekStart;
	}
	
	public static LocalDate parseDate(String date) {
		return parseDate(date, FORMAT_MM_DD_YYYY);
	}
	
	public static LocalDate parseDate(String date, String format) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		
		return LocalDate.parse(date, df);
	}

	public static String formatDate(LocalDate date, String format) {
		if (date == null) {
			return "";
		}
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
		return date.format(df);
	}

	public static List<LocalDate> getWeekStartDates(int month) {
		LocalDate monthStartDate = getMonthStartDate(month);
		LocalDate monthEndDate = getMonthEndDate(month);
		
		List<LocalDate> weekStartDates = new ArrayList<LocalDate>();
		
		do {
			weekStartDates.add(monthStartDate);
			monthStartDate = getNextWeekStartDate(monthStartDate);
		} while (monthStartDate.isBefore(monthEndDate));
		
		System.out.println(weekStartDates);
		
		weekStartDates.forEach(ws -> {
			LocalDate weekEndDate = getWeekEndDate(ws);
			System.out.println(weekEndDate);
		});
		
		return weekStartDates;
	}
	
	public static LocalDate getWeekEndDate(LocalDate weekStartDate) {
		LocalDate weekEndDate = null;
		if (weekStartDate != null) {
			if (weekStartDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
				weekEndDate = weekStartDate.plusDays(6);
				if (weekStartDate.getMonth().equals(weekEndDate.getMonth()) == false) {
					weekEndDate = getMonthEndDate(weekStartDate.getMonthValue());
				}
			} else {
				weekEndDate = getNextWeekStartDate(weekStartDate);
				weekEndDate = weekEndDate.minusDays(1);
			}
		}
		
		return weekEndDate;
	}

	/**
	 * @param monthStartDate
	 * @return
	 */
	public static LocalDate getMonthEndDate(int mon) {
		LocalDate monthStartDate = getMonthStartDate(mon);
		
		return monthStartDate.plusDays(monthStartDate.lengthOfMonth() - 1);
	}

	/**
	 * @param mon
	 * @return
	 */
	public static LocalDate getMonthStartDate(int month) {
		Month mon = Month.of(month);

		return LocalDate.of(2018, mon, 1);
	}
}
