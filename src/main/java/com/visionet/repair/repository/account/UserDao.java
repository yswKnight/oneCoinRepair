package com.visionet.repair.repository.account;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.visionet.repair.entity.account.User;

public interface UserDao extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

	User findByUserName(String loginName);

	List<User> findByOrgId(String orgId);
	
	@Query("from User where orgId=?1")
	List<User> findByOrgId(String orgId,Pageable pageable);
}
