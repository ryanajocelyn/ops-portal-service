/**
 * 
 */
package com.cognizant.pmo.utils;

import java.util.Date;

/**
 * @author 238209
 *
 */
public class ProcessTimer {
	
	private static Date startTime;
	private static Date endTime;	
	
	public static void startTimer() {
		startTime = new Date(System.currentTimeMillis());
	}
	
	public static void endTimer(String message) {
		endTime = new Date(System.currentTimeMillis());
		System.out.println(message + ": Time taken = " 
								+ (endTime.getTime() - startTime.getTime())/1000
								+ " seconds.");
	}
}
