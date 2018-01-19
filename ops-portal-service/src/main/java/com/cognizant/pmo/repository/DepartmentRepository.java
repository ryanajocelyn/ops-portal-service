/**
 * 
 */
package com.cognizant.pmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pmo.entity.Department;

/**
 * @author 238209
 *
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findByDepartmentName(String departmentName);
}
