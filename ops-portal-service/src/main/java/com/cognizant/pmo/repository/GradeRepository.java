/**
 * 
 */
package com.cognizant.pmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pmo.entity.Grade;

/**
 * @author 238209
 *
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {

	List<Grade> findByGradeShortDesc(String gradeShortDesc);
	
}
