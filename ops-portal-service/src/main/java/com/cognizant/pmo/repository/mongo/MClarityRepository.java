/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.pmo.entity.mongo.MClarity;

/**
 * @author 238209
 *
 */
public interface MClarityRepository extends MongoRepository<MClarity, BigInteger> {

	@Query("{'resourceWorkdayId': ?0, 'esiTaskId': ?1, 'weekStartDate': ?2}") 
	public List<MClarity> findByResourceIdAndTaskIdAndStartDateQuery(String resWorkdayId, String taskId, LocalDate weekStartDate);

	@Query("{'resourceWorkdayId': ?0, 'weekStartDate': {$gte : ?1}, 'weekEndDate': {$lt : ?2} }") 
	public List<MClarity> findByResourceIdAndStartDateAndEndDateQuery(String resWorkdayId, LocalDate weekStartDate, LocalDate endDate);

	@Query("{'resourceWorkdayId': ?0}")
	public List<MClarity> findByResourceIdQuery(String resWorkdayId);
}
