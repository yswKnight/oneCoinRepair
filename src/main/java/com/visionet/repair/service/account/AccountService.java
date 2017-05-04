package com.visionet.repair.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import com.visionet.repair.entity.account.User;
import com.visionet.repair.repository.account.UserDao;
import com.visionet.repair.service.BaseService;


/**
 * 用户管理业务类.
 * 
 * @author xt
 */
@Service
public class AccountService extends BaseService {
	
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private UserDao userDao;

	// /**
	// * 在保存用户时,发送用户修改通知消息, 由消息接收者异步进行较为耗时的通知邮件发送.
	// *
	// * 如果企图修改超级用户,取出当前操作员用户,打印其信息然后抛出异常.
	// *
	// */
	// @Transactional(readOnly = false)
	// public void saveUser(User user) {
	//
	// if(user.getId()==null){
	// throwException(BusinessStatus.REQUIRE,"id is null!");
	// }
	//
	// this.checkUserInfo(user);
	//
	// // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	// if (StringUtils.isNotBlank(user.getPlainPassword())) {
	// entryptPassword(user);
	// }
	//
	//
	// userDao.save(user);
	// }

	// private void checkUserInfo(User user){
	// if(userDao.checkByLoginName(user.getLoginName(), user.getId()) != null){
	// throw new
	// RestException(MessageSourceHelper.GetMessages("register.loginName.exist"));
	// }
	//// if(userDao.checkByAliasName(user.getAliasName(),
	// user.getOrgId(),user.getId()) > 0){
	//// throw new
	// RestException(MessageSourceHelper.GetMessages("register.aliasName.exist"));
	//// }
	// if (isSupervisor(user.getId())) {
	// logger.warn("Operator{}want to modify admin!",
	// BaseController.getCurrentUserName());
	// throw new
	// ServiceException(MessageSourceHelper.GetMessages("app.service.account.AccountService.modify.admin"));
	// }
	// }

	/*
	 * @Transactional(readOnly = false) public void updateUserRole(Long
	 * userId,Set<Role> roleSet) { User po = userDao.findOne(userId);
	 * po.setRoleList(roleSet); userDao.save(po); } //
	 */
	// /**
	// * 用户修改自己密码
	// * 领导可以锁定秘书功能
	// * @param user
	// */
	// @Transactional(readOnly = false)
	// public void updateUserPasswd(User user) {
	// User po = userDao.findOne(user.getId());
	//
	// // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	// if (StringUtils.isNotBlank(user.getPlainPassword())) {
	// entryptPassword(user);
	// po.setPassword(user.getPassword());
	// po.setPasswordSalt(user.getPasswordSalt());
	// }
	//
	// userDao.save(po);
	// }

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash CAS 接口不支持salt
	 */
	protected static void entryptPassword(User user) {
/*		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setPasswordSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));*/
	}

	public static String getEntryptPassword(String plainPassword, String salt) {
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), Encodes.decodeHex(salt), HASH_INTERATIONS);
		return Encodes.encodeHex(hashPassword);
	}

	// /**
	// * 判断是否超级管理员.
	// */
	// public static boolean isSupervisor(User user) {
	// return ((user.getId() != null) && (user.getId() == 1L));
	// }
	// public static boolean isSupervisor(Long userId) {
	// return ((userId != null) && (userId == 1L));
	// }

	/**
	 * 判断是否超级管理员或辅助管理员
	 */
	public boolean isAdmin(Long userId) {
		if ((userId != null)) {
			return true;
		}

		User user = getUser(userId);
		if (user == null)
			return false;

		/*
		 * if(SysConstants.ADMIN.equals(user.getRoleNames()) ||
		 * SysConstants.SUBADMIN.equals(user.getRoleNames())){ return true; }
		 */

		return false;
	}

	/**
	 * 根据用户id查询用户
	 * 
	 * @param id
	 *            用户id
	 * @return
	 */
	public User getUser(Long id) {
		return null;
	}

	/**
	 * 获取当前用户数量.
	 */
	public Long getUserCount() {
		return userDao.count();
	}

	public User findByLoginName(String loginName) {
		User user = userDao.findByUserName(loginName);
		return user;
	}
}
