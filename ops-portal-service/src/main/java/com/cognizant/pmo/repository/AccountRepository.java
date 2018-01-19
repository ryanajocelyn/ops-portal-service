/**
 * 
 */
package com.cognizant.pmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.pmo.entity.Account;

/**
 * @author 238209
 *
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
