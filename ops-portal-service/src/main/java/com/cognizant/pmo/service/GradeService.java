/**
 * 
 */
package com.cognizant.pmo.service;

import com.cognizant.pmo.entity.Grade;

/**
 * @author 238209
 *
 */
public interface GradeService {

	public Grade getGradeByShortName(String gradeShortName);
}
