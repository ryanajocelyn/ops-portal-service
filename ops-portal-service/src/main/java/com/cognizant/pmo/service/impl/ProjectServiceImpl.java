/**
 * 
 */
package com.cognizant.pmo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cognizant.pmo.bo.AccountBo;
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

	@Override
	public List<AccountBo> getAllProjectAccounts() {
		List<MProject> projectList = mProjectRepository.findAll();
		
		List<AccountBo> accountList = 
					projectList.stream()
					.filter(distinctByKey(a -> a.getAccountId()))
					.map(p -> {
						AccountBo account = new AccountBo();
						account.setAccountId(p.getAccountId());
						account.setAccountName(p.getAccountName());
						
						return account;
					})
					.collect(Collectors.toList());

		
		return accountList;
	}
	
	public static void main(String[] args) {
		List<AccountBo> test = new ArrayList<AccountBo>();
		
		AccountBo account = new AccountBo();
		account.setAccountId(1L);
		test.add(account);

		AccountBo account1 = new AccountBo();
		account1.setAccountId(1L);
		test.add(account1);

		AccountBo account2 = new AccountBo();
		account2.setAccountId(2L);
		test.add(account2);

		AccountBo account3 = new AccountBo();
		account3.setAccountId(3L);
		test.add(account3);
		
		System.out.println(test);
		System.out.println(account.equals(account1));
		System.out.println(new HashSet<AccountBo>(test));
		
		test = test.stream().distinct().collect(Collectors.toList());
		
		System.out.println(test);
		
		test = test.stream().filter(distinctByKey(a -> a.getAccountId())).collect(Collectors.toList());
		System.out.println(test);
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
