package com.visionet.repair.repository.account;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.visionet.repair.entity.account.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, String>, JpaSpecificationExecutor<Role>{

}
