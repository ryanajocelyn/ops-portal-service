/**
 * 
 */
package com.cognizant.pmo.service;

import java.util.List;

import com.cognizant.pmo.bo.AccountBo;
import com.cognizant.pmo.entity.mongo.MProject;

/**
 * @author 238209
 *
 */
public interface ProjectService {

	public List<MProject> getAllProjects();
	
	public boolean createProject(MProject project);
	
	public List<AccountBo> getAllProjectAccounts();
}
