/**
 * 
 */
package com.cognizant.pmo.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognizant.pmo.entity.mongo.Test;

/**
 * @author 238209
 *
 */
public interface MTestRepository extends MongoRepository<Test, Long> {

}
