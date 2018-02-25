/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cognizant.pmo.entity.mongo.MProject;
import com.cognizant.pmo.repository.mongo.MProjectRepository;
import com.cognizant.pmo.service.ProjectService;

/**
 * @author 238209
 *
 */
@Component
public class ProjectServiceImpl implements ProjectService {

	@Resource
	private MProjectRepository mProjectRepository;
	
	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.ProjectService#getAllProjects()
	 */
	@Override
	public List<MProject> getAllProjects() {
		return mProjectRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.cognizant.pmo.service.ProjectService#createProject(com.cognizant.pmo.entity.mongo.MProject)
	 */
	@Override
	public boolean createProject(MProject project) {
		mProjectRepository.save(project);
		
		return true;
	}

}
