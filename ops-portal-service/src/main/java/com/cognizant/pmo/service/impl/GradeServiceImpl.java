/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.cognizant.pmo.entity.Grade;
import com.cognizant.pmo.repository.GradeRepository;
import com.cognizant.pmo.service.GradeService;

/**
 * @author 238209
 *
 */
@Component
public class GradeServiceImpl implements GradeService {

	@Resource
	private GradeRepository gradeRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.GradeService#getGradeByShortName(java.lang.String)
	 */
	@Override
	public Grade getGradeByShortName(String gradeShortName) {
		List<Grade> gradeList = gradeRepository.findByGradeShortDesc(gradeShortName); 
		
		if (CollectionUtils.isNotEmpty(gradeList)) {
			return gradeList.get(0);
		}
		
		return null;
	}

}
