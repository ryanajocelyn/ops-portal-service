/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.cognizant.pmo.entity.Department;
import com.cognizant.pmo.repository.DepartmentRepository;
import com.cognizant.pmo.service.DepartmentService;

/**
 * @author 238209
 *
 */
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentRepository departmentRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.DepartmentService#getDepartmentByDepartmentName(java.lang.String)
	 */
	@Override
	public Department getDepartmentByDepartmentName(String deptName) {
		List<Department> departmentList = departmentRepository.findByDepartmentName(deptName); 

		if (CollectionUtils.isNotEmpty(departmentList)) {
			return departmentList.get(0);
		}
		
		return null;
	}

}
