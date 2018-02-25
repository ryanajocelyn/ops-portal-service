/**
 * 
 */
package com.cognizant.pmo.bo;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.cognizant.pmo.utils.ExcelFileReader;


/**
 * @author 238209
 *
 */
public class Record {

	public static Logger logger = Logger.getLogger(ExcelFileReader.class);
	
	public static String DEFAULT_DATETIME_FORMAT = "dd/mm/yyyy";
	public static String DATETIME_FORMAT_DD_MON_YY = "dd-MMM-yyyy";
	public static String DEFAULT_DATE_FORMAT = "dd/mm/yyyy 00:00:00";
	
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


	public Date getDateTimeValue(String key) {
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

	public Date getDateValue(String key) {
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
