package com.visionet.repair.service.account;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Objects;
import com.visionet.repair.common.constant.SysConstants;
import com.visionet.repair.entity.account.Role;
import com.visionet.repair.entity.account.User;

public class ShiroDbRealm extends AuthorizingRealm {

	protected AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 认证回调函数,登录时调用.doGetAuthenticationInfo
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		User user = accountService.findByLoginName(token.getUsername());

		if (user == null)
			return null;

		// 账号已被停用
		if (user.getActivity().equals(SysConstants.USER_ACTIVITY_DISABLED)) {
			throw new DisabledAccountException();
		}
		// 账号没有权限，权限为空
//		if (user.getUserRoleses() == null || user.getUserRoleses().isEmpty()) {
//			throw new RestException("role can not be null!");
//		}
//		// 组织信息为空
//		if (user.getOrgInfo() == null) {
//			throw new RestException("organization can not be null!");
//		}
		String secName = null;
		List<Role> roles = roleService.findByUserId(user.getId());
		
		// byte[] salt = Encodes.decodeHex(user.getPasswordSalt());
		return new SimpleAuthenticationInfo(
				new ShiroUser(user.getId(), user.getUserName(), user.getActivity()), user.getPassword(), null, getName());
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		User user = accountService.findByLoginName(shiroUser.userName);

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Role> roles = roleService.findByUserId(user.getId());
		for (Role role : roles) {
			info.addRole(role.getName());
		}

		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String userId;
		public String userName;
		public Integer activity;
		
		public String locale;

		public ShiroUser(String userName) {
			this.userName = userName;
		}

		public ShiroUser(String userId, String userName) {
			this.userId = userId;
			this.userName = userName;
		}

		public ShiroUser(String userId, String userName, Integer activity) {
			this.userId = userId;
			this.userName = userName;
			this.activity = activity;

		}

		public String getUserId() {
			return this.userId;
		}


		public String getUserName() {
			return userName;
		}

		public Integer getActivity() {
			return activity;
		}

		public String getLocale() {
			return locale;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return userName;
		}

		/**
		 * 重载hashCode,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(userName);
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShiroUser other = (ShiroUser) obj;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			return true;
		}
	}
}
