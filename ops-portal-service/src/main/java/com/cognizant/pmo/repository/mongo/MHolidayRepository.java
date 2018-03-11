/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.pmo.entity.mongo.MHoliday;

/**
 * @author 238209
 *
 */
public interface MHolidayRepository extends MongoRepository<MHoliday, BigInteger> {
	
	@Query("{'date': { $gt : ?0} }") 
	public List<MHoliday> findByGtDateQuery(LocalDate date);
	
	/*@Query("{'_id': { $in : ?0} }") 
	public List<MAssociate> findByAssociateIdsQuery(List<BigInteger> associateIdList);*/
}
