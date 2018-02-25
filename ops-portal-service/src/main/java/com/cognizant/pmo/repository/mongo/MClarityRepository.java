/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.util.Date;
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
	public List<MClarity> findByResourceIdAndTaskIdAndStartDateQuery(Long resWorkdayId, String taskId, Date weekStartDate);

	@Query("{'resourceWorkdayId': ?0}")
	public List<MClarity> findByResourceIdQuery(Long resWorkdayId);
	
}
