/**
 * 
 */
package com.cognizant.pmo.bo;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.cognizant.pmo.utils.Constants;
import com.cognizant.pmo.utils.ExcelFileReader;


/**
 * @author 238209
 *
 */
public class Record {

	public static Logger logger = Logger.getLogger(ExcelFileReader.class);
	
	public static String DEFAULT_DATETIME_FORMAT = "MM/dd/yyyy";
	public static String DATETIME_FORMAT_DD_MON_YY = "dd-MMM-yy";
	public static String DATETIME_FORMAT_DD_MON_YYYY = "dd-MMM-yyyy";
	public static String DATETIME_FORMAT_DD_MON = "dd-MMM-";
	public static String DATETIME_FORMAT_D_MON = "d-MMM-";
	public static String DEFAULT_DATETIME_FORMAT_MM_DD = "MM/dd/";
	public static String DEFAULT_DATE_FORMAT = "MM/dd/yyyy 00:00:00";
	public static String DEFAULT_TIME_FORMAT = " hh:mm:ss a";
	
	private Map<String, Object> attributes;
	
	private List<String> recordHeaders;

	public BigInteger getBigIntValue (String key) {
		String value = getStringValue(key);
		if (value != null) {
			return new BigInteger(StringUtils.substringBefore(value, "."));
		}
		
		return new BigInteger(String.valueOf(Long.MIN_VALUE));
	}

	public Double getDoubleValue (String key) {
		Double doubleValue = Double.MIN_VALUE;
		
		Object value = getValue(key);
		
		try {
			if (value != null) {
				String escapedValue = StringUtils.replace(value.toString(), ",", "");
				doubleValue = new Double(escapedValue);
			}
		} catch (NumberFormatException e) {
			logger.error("Error while parsing " + key, e);
			doubleValue = Double.MIN_VALUE;
		}
		
		return doubleValue;
	}

	
	public Long getLongValue (String key) {
		Long longValue = Long.MIN_VALUE;
		
		Object value = getValue(key);
		
		try {
			if (value != null) {
				longValue = new Double(value.toString()).longValue();
			}
		} catch (NumberFormatException e) {
			logger.error("Error while parsing " + key, e);
			longValue = Long.MIN_VALUE;
		}
		
		return longValue;
	}
	
	public Integer getIntValue (String key) {
		Integer intValue = Integer.MIN_VALUE;
		
		Object value = getValue(key);
		
		try {
			if (value != null) {
				intValue = new Double(value.toString()).intValue();
			}
		} catch (NumberFormatException e) {
			logger.error("Error while parsing " + key, e);
			intValue = Integer.MIN_VALUE;
		}
		
		return intValue;
	}
	
	public String getStringValue (String key) {
		Object value = getValue(key);
		
		if (value != null) {
			return value.toString();
		}
		
		return null;
	}


	public LocalDateTime getDateTimeValue(String key) {
		LocalDateTime dateValue = null;
		Object value = getValue(key);

		if (value != null) {
			String formattedDate = formatDate(value.toString());
			String datePattern = getDatePattern(formattedDate);
			if (datePattern != null) {
				DateTimeFormatter df = 
						new DateTimeFormatterBuilder().appendPattern(datePattern)
						.appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 4, 
								LocalDate.now().minusYears(60))
						.appendOptional(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT))
						.toFormatter();

				dateValue = LocalDateTime.parse(formattedDate, df);
			}
		}
		
		return dateValue;
	}

	public static void main(String[] args) {
		LocalDate dateValue = null;
		LocalDateTime dateTimeValue = null;
		
		String[] dates = new String[] {"1-Mar-98", "01-Mar-98", "01/01/2018", "1/1/2018", "01/07/2018 03:00:04 PM",
				"02/23/2018  05:00:04 AM", "12/31/2017"};
		
		for (String actualDate : dates) {
			String date = formatDate(actualDate);
			
			String datePattern = new Record().getDatePattern(date);
			if (datePattern != null) {
				DateTimeFormatterBuilder dfb = new DateTimeFormatterBuilder();
				dfb.appendPattern(datePattern)
				//.appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
				.appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 4, LocalDate.now().minusYears(60))
				.appendOptional(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
				
				DateTimeFormatter df = dfb.toFormatter();
				dateValue = LocalDate.parse(date, df);
				
				if (date.contains(":")) {
					dateTimeValue = LocalDateTime.parse(date, df);
				}
			}
			
			System.out.println(actualDate + "(" + datePattern + ") = " + dateValue + " :: " + dateTimeValue);
		}
	}
	
	private static String formatDate(String date) {
		date = StringUtils.replacePattern(date, "[ ]+", " ");
		
		String retVal = splitAppendBySeparator(date, Constants.DASH);
		if (StringUtils.isBlank(retVal)) {
			retVal = splitAppendBySeparator(date, Constants.SLASH);
			
			if (StringUtils.isBlank(retVal)) {
				retVal = date;
			}
		}
		
		System.out.println("Formatted Date = " + retVal);
		return retVal;
	}

	private static String splitAppendBySeparator(String date, String separator) {
		StringBuilder returnVal = new StringBuilder();
		
		String[] dateFields = StringUtils.split(date, separator);
		if (dateFields.length > 1) {
			for (String dateField : dateFields) {
				if (dateField.length() == 1) {
					returnVal.append("0");
				} 

				returnVal.append(dateField);
				returnVal.append(separator);
			}
		}
		
		return StringUtils.substringBeforeLast(returnVal.toString(), separator);
	}

	public LocalDate getDateValue(String key) {
		return getDateValue(key, null);
	}
	
	public LocalDate getDateValue(String key, String datePattern) {
		LocalDate dateValue = null;
		Object value = getValue(key);

		if (value != null) {
			String formattedDate = formatDate(value.toString());
			
			if (datePattern == null) {
				datePattern = getDatePattern(formattedDate);
			}
			
			if (datePattern != null) {
				DateTimeFormatter df = 
						new DateTimeFormatterBuilder().appendPattern(datePattern)
						.appendValueReduced(ChronoField.YEAR_OF_ERA, 2, 4, 
								LocalDate.now().minusYears(60))
						.appendOptional(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT))
						.toFormatter();

				dateValue = LocalDate.parse(formattedDate, df);
			}
		}
		
		return dateValue;
	}
	
	private String getDatePattern (Object value) {
		String datePattern = null;
		
		if (value != null) {
			Pattern pattern = Pattern.compile("[0-9]*-[a-zA-Z]*-[0-9]*");
			Matcher matcher = pattern.matcher(value.toString());
			if (StringUtils.equalsIgnoreCase("No End Date", value.toString())) {
				logger.info("Not Parsable Date: " + value.toString());
			} else if (matcher.matches()) { 
				datePattern = DATETIME_FORMAT_DD_MON;
			} else {
				datePattern = DEFAULT_DATETIME_FORMAT_MM_DD;
			}
		}
		
		return datePattern;
	}
	
	public Date getDateTimeValueLegacy(String key) {
		Date dateValue = null;
		Object value = getValue(key);

		try {
			if (value != null) {
				Pattern pattern = Pattern.compile("[0-9]*-[a-zA-Z]*-[0-9]*");
				Matcher matcher = pattern.matcher(value.toString());
				if (StringUtils.equalsIgnoreCase("No End Date", value.toString())) {
					logger.info("Not Parsable Date: " + value.toString());
				} else if (matcher.matches()) { 
					SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_DD_MON_YY);
					dateValue = sdf.parse(value.toString());
				} else {
					SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
					dateValue = sdf.parse(value.toString());
				}
			}
		} catch (ParseException e) {
			logger.error("Error while parsing Date of Joining", e);
		}
		
		return dateValue;
	}

	public Date getDateValueLegacy(String key) {
		Date dateValue = null;
		Object value = getValue(key);

		try {
			if (value != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
				dateValue = sdf.parse(value.toString());
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateValue);
				
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				
				dateValue = cal.getTime();
			}
		} catch (ParseException e) {
			logger.error("Error while parsing Date of Joining", e);
		}
		
		return dateValue;
	}

	
	public Object getValue (String key) {
		return attributes.get(key);
	}
	
	public void addAttribute(String key, Object value) {
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
		}
	
		addRecordHeader(key);
		attributes.put(key, value);
	}
	
	public void addRecordHeader(String header) {
		if (recordHeaders == null) {
			recordHeaders = new ArrayList<String>();
		}
		
		if (recordHeaders.contains(header) == false) {
			recordHeaders.add(header);
		}
	}
	
	/**
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the recordHeaders
	 */
	public List<String> getRecordHeaders() {
		return recordHeaders;
	}

	/**
	 * @param recordHeaders the recordHeaders to set
	 */
	public void setRecordHeaders(List<String> recordHeaders) {
		this.recordHeaders = recordHeaders;
	}
}
