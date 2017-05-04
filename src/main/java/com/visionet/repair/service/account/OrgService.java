package com.visionet.repair.service.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.visionet.repair.common.utils.PageInfo;
import com.visionet.repair.entity.account.Org;
import com.visionet.repair.repository.account.OrgDao;
import com.visionet.repair.service.BaseService;
import com.visionet.repair.vo.account.OrgVo;


/****************************
 * @author 王永圣
 *@param  org类的业务层
 */
@Service
public class OrgService extends BaseService {
	
	@Autowired
	private OrgDao orgDao;
	
	/*************************
	 * @param  添加组织机构
	 */
	public Boolean saveOrg(OrgVo orgVo){
			Org org=new Org();
			org.setOrgName(orgVo.getOrgName());
			org.setOrgAddress(orgVo.getOrgAddress());
			//判断组织名是否为空
			if((org.getOrgName().toString()!="")||(org.getOrgName().length()!=0)){
				//判断输入的  组织名称和地址的字符长度  是否超出指定字符长度
				if(orgVo.getOrgName().length()<=10&&orgVo.getOrgAddress().length()<=15){
					orgDao.save(org);
					return true;
				}else{
					System.err.println("您要添加的名字或地址超出指定字符长度，请重新输入！");
					return false;
				}
			}else{
				System.err.println("您要添加的组织名不能为空");
				return false;
			}
	}
	/***************************
	 * @param 修改
	 */
	public boolean updateOrg(OrgVo orgVo){
		//先将id从dao层里面取出来，然后带着id去寻找对应的信息进行修改
		Org org=orgDao.findOne(orgVo.getId());
		org.setOrgName(orgVo.getOrgName());
		org.setOrgAddress(orgVo.getOrgAddress());
		//save===saveORupdate
		//判断组织名是否为空
		if((org.getOrgName().toString()!="")||(org.getOrgName().length()!=0)){
			//判断输入的  组织名称和地址的字符长度  是否超出指定字符长度
			if(orgVo.getOrgName().length()<=10&&orgVo.getOrgAddress().length()<=15){
				orgDao.save(org);
				return true;
			}else{
				System.err.println("您要修改的名字或地址超出指定字符长度，请重新输入！");
				return false;
			}
		}else{
			System.err.println("您要修改的组织名不能为空");
			return false;
		}
	} 
	/***************************
	 * @param 删除
	 */
	public boolean deleteOrg(String oid){
		orgDao.delete(oid);
		return true;
	}
	/***************************
	 * @param 根据分页查看全部信息
	 */
	public Page<Org> listOrg(OrgVo orgVo){
		PageInfo pageinfo=orgVo.getPageInfo();
		if(pageinfo==null){
			pageinfo = new PageInfo();
		}
		
		return orgDao.findAll(pageinfo.getPageRequestInfo());
	}
	/***************************
	 * @param 单查
	 */
	public List<Org> findByOrgId(String oid){
		List<Org> orgs =new ArrayList<Org>();
		Org org=orgDao.findOne(oid);
		orgs.add(org);
 		return orgs;
	}
}
