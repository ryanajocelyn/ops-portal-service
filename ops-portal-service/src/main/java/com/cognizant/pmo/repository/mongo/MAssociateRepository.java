/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cognizant.pmo.entity.mongo.MAssociate;

/**
 * @author 238209
 *
 */
public interface MAssociateRepository extends MongoRepository<MAssociate, BigInteger> {
	
	@Query("{'projectAllocations.projectId': { $in : ?0} }") 
	public List<MAssociate> findByProjectIdQuery(List<BigInteger> projectIdList);
	
	@Query("{'_id': { $in : ?0} }") 
	public List<MAssociate> findByAssociateIdsQuery(List<BigInteger> associateIdList);

	public List<MAssociate> findByResourceWorkdayIdNull();

	@Query("{'projectAllocations.projectId': { $in : ?0}, 'projectAllocations.assignmentPercentage' : { $gt : ?1} }") 
	public List<MAssociate> findByProjectIdAndAllocationPercentQuery(List<BigInteger> projectIdList, 
																		int allocationPercent);

	public List<MAssociate> findByFgWorkerId(String workdayId);
}
