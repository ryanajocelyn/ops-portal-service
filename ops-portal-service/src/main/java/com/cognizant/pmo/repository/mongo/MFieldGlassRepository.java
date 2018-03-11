/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.pmo.entity.mongo.MFieldGlass;

/**
 * @author 238209
 *
 */
public interface MFieldGlassRepository extends MongoRepository<MFieldGlass, BigInteger> {
	
	@Query("{'fgWorkerId': ?0, 'fgStartDate': ?1}") 
	public List<MFieldGlass> findByResourceIdAndStartDateQuery(String fgWorkerId, LocalDate startDate);

	@Query("{'fgWorkerId': ?0, 'fgStartDate': {$gte : ?1}, 'fgEndDate': {$lt : ?2} }") 
	public List<MFieldGlass> findByResourceIdAndStartDateAndEndDateQuery(String fgWorkerId, LocalDate startDate, LocalDate endDate);

	@Query("{'fgWorkerId': ?0}")
	public List<MFieldGlass> findByResourceIdQuery(String fgWorkerId);
}
