/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.pmo.entity.mongo.MAssociate;

/**
 * @author 238209
 *
 */
public interface MAssociateRepository extends MongoRepository<MAssociate, BigInteger> {
	
	@Query("{'projectAllocations.projectId': ?0, 'fgStartDate': ?1}") 
	public List<MAssociate> findByProjectIdQuery(List<BigInteger> projectIdList);
}
