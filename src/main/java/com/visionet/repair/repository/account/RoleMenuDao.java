package com.visionet.repair.repository.account;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.visionet.repair.entity.account.RoleMenu;

public interface RoleMenuDao extends PagingAndSortingRepository<RoleMenu, String>, JpaSpecificationExecutor<RoleMenu> {

}
