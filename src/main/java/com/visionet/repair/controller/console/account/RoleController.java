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
import com.visionet.repair.entity.account.Role;
import com.visionet.repair.service.account.RoleService;
import com.visionet.repair.vo.account.RoleVo;

@Controller
@RequestMapping("/console/account/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody RoleVo roleVo) throws Exception {
		if(roleService.saveRole(roleVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("添加失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody RoleVo roleVo) throws Exception {
		if(roleService.updateRole(roleVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("修改失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testList(@RequestBody RoleVo roleVo) throws Exception {
		Page<Role> page = roleService.listUser(roleVo);
        List<RoleVo> listVo = BeanConvertMap.mapList(page.getContent(), RoleVo.class);
        return new ResponseEntity<Page<RoleVo>>(getPageByList(page, listVo, RoleVo.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testDelete(@PathVariable String userId) throws Exception {
		if(roleService.deleteRole(userId)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("删除成功"), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("删除失败", HttpStatus.OK);
		}
	}
}
