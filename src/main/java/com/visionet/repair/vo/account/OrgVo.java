package com.visionet.repair.vo.account;

import com.visionet.repair.vo.BaseVo;

/****************************8
 * 
 * @author 王永圣
 *	@param 组织命令类
 */
public class OrgVo extends BaseVo {

		//组织名称
		private String orgName;
		//地址
		private String orgAddress;
		
		
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		public String getOrgAddress() {
			return orgAddress;
		}
		public void setOrgAddress(String orgAddress) {
			this.orgAddress = orgAddress;
		}
		
		
}
