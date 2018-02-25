/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.util.Date;
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
	public List<MFieldGlass> findByResourceIdAndStartDateQuery(String fgWorkerId, Date startDate);

	@Query("{'fgWorkerId': ?0}")
	public List<MFieldGlass> findByResourceIdQuery(String fgWorkerId);
}
