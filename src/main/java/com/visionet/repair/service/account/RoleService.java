package com.visionet.repair.service.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.visionet.repair.common.utils.PageInfo;
import com.visionet.repair.entity.account.Role;
import com.visionet.repair.entity.account.UserRole;
import com.visionet.repair.repository.account.RoleDao;
import com.visionet.repair.repository.account.UserRoleDao;
import com.visionet.repair.service.BaseService;
import com.visionet.repair.vo.account.RoleVo;

@Service
public class RoleService extends BaseService {

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	/********************
	 * @param roleVo
	 * @param 添加角色
	 */
	public Boolean saveRole(RoleVo roleVo) {
		Role role=new Role();
		role.setName(roleVo.getName());
		//role.setType(roleVo.getType());
		roleDao.save(role);
		return true;
	}

	public boolean updateRole(RoleVo roleVo) {
		Role role=roleDao.findOne(roleVo.getId());
		role.setName(roleVo.getName());
		//role.setType(roleVo.getType());
		roleDao.save(role);
		return true;
	}

	public boolean deleteRole(String userId) {
		roleDao.delete(userId);
		return true;
	}

	public Page<Role> listUser(RoleVo roleVo) {
		PageInfo pageInfo = roleVo.getPageInfo();
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return roleDao.findAll(pageInfo.getPageRequestInfo());
	}
	
	public List<Role> findByUserId(String userId){
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = userRoleDao.findByUserId(userId);
		for (UserRole userRole : userRoles) {
			Role role = roleDao.findOne(userRole.getRoleId());
			roles.add(role);
		}
		return roles;
	}
}
