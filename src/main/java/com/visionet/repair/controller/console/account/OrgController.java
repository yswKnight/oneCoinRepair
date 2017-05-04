package com.visionet.repair.controller.console.account;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visionet.repair.common.utils.BeanConvertMap;
import com.visionet.repair.controller.BaseController;
import com.visionet.repair.entity.account.Org;
import com.visionet.repair.service.account.OrgService;
import com.visionet.repair.vo.account.OrgVo;


/*********************
 * @author 王永圣
 * @param 控制层
 */
@Controller
@RequestMapping("/console/account/org")
public class OrgController extends BaseController {
	
	@Autowired
	private OrgService orgService;
	
	/***********************************
	 * @param  根据分页查看全部信息
	 */
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<?> list(@RequestBody OrgVo orgVo) throws Exception{
		Page<Org> page=orgService.listOrg(orgVo);
		List<OrgVo> listVo=BeanConvertMap.mapList(page.getContent(), OrgVo.class);
		return new ResponseEntity<Page<OrgVo>>(getPageByList(page, listVo, OrgVo.class),HttpStatus.OK);
	}
	/***********************************
	 * @param  添加
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody OrgVo orgVo) throws Exception{
		if(orgService.saveOrg(orgVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("添加成功"),HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("添加失败",HttpStatus.OK);
		}
	}
	/***********************************
	 * @param  修改
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uodate(@RequestBody OrgVo orgVo) throws Exception{
		if(orgService.updateOrg(orgVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("修改成功"),HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("修改失败",HttpStatus.OK);
		}
	}
	/***********************************
	 * @param  删除
	 */
	@RequestMapping(value="/delete/{orgId}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable String orgId) throws Exception{
		if(orgService.deleteOrg(orgId)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("删除成功"),HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("删除失败",HttpStatus.OK);
		}
	}
	/***********************************
	 * @param  单查
	 */
	
	@RequestMapping(value="/listById/{orgId}",method=RequestMethod.GET)
	@ResponseBody
	public  ResponseEntity<?> listById(@PathVariable String orgId) throws Exception{
		orgService.findByOrgId(orgId);
		//List<Org> list=orgService.findByOrgId(orgId);
		return new ResponseEntity<Map<String,String>>(getSuccMap("成功"),HttpStatus.OK);
	}
}
