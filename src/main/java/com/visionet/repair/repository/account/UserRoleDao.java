package com.visionet.repair.repository.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.visionet.repair.entity.account.UserRole;

public interface UserRoleDao extends PagingAndSortingRepository<UserRole, String>, JpaSpecificationExecutor<UserRole>{

	public List<UserRole> findByUserId(String userId);
	
}
