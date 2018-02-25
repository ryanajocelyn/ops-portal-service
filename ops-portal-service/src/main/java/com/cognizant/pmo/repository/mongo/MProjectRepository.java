/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognizant.pmo.entity.mongo.MProject;

/**
 * @author 238209
 *
 */
public interface MProjectRepository extends MongoRepository<MProject, BigInteger> {

	public List<MProject> findByAccountId(Long accountId);
	
}
