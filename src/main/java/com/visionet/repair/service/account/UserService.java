package com.visionet.repair.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.visionet.repair.common.utils.PageInfo;
import com.visionet.repair.entity.account.Org;
import com.visionet.repair.entity.account.Role;
import com.visionet.repair.entity.account.User;
import com.visionet.repair.repository.account.UserDao;
import com.visionet.repair.service.BaseService;
import com.visionet.repair.vo.account.UserVo;

/**
 * 用户管理业务类.
 * 
 * @author xt
 */
@Service
public class UserService extends BaseService {

	@Autowired
	private UserDao userDao;

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public User findByuserLoginName(String loginName) {
		return userDao.findByUserName(loginName);
	}

	public Boolean saveUser(UserVo userVo) {
		User user = new User();
		Org org=new Org();
		user.setUserName(userVo.getUserName());
		user.setUserChName(userVo.getUserChName());
		user.setPassword(userVo.getPassword());
		user.setPhone(userVo.getPhone());
		//组织id
		org.setId(userVo.getOrgId());
		user.setOrgId(org.getId());
		//用户权限id
		user.setRoleId(userVo.getRoleId());
		user.setEmail(userVo.getEmail());
		
		userDao.save(user);
		return true;
	}

	public boolean updateUser(UserVo userVo) {
		Org org=new Org();
		//Role role=new Role();
		User user = userDao.findOne(userVo.getId());
		user.setUserName(userVo.getUserName());
		user.setUserChName(userVo.getUserChName());
		user.setPassword(userVo.getPassword());
		user.setPhone(userVo.getPhone());
		//组织id
		org.setId(userVo.getOrgId());
		user.setOrgId(org.getId());
		//用户权限id
		user.setRoleId(userVo.getRoleId());
		user.setEmail(userVo.getEmail());
		userDao.save(user);
		return true;
	}

	public boolean deleteUser(String userId) {
		userDao.delete(userId);
		return true;
	}

	public Page<User> listUser(UserVo userVo) {
		PageInfo pageInfo = userVo.getPageInfo();
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		return userDao.findAll(pageInfo.getPageRequestInfo());
	}
}
