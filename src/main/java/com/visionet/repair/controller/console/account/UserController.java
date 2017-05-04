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
import com.visionet.repair.entity.account.User;
import com.visionet.repair.service.account.UserService;
import com.visionet.repair.vo.account.UserVo;

@Controller
@RequestMapping("/console/account/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody UserVo userVo) throws Exception {
		if(userService.saveUser(userVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("添加失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody UserVo userVo) throws Exception {
		if(userService.updateUser(userVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("修改失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testList(@RequestBody UserVo userVo) throws Exception {
		Page<User> page = userService.listUser(userVo);
        List<UserVo> listVo = BeanConvertMap.mapList(page.getContent(), UserVo.class);
        return new ResponseEntity<Page<UserVo>>(getPageByList(page, listVo, UserVo.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testDelete(@PathVariable String userId) throws Exception {
		if(userService.deleteUser(userId)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("删除成功"), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("删除失败", HttpStatus.OK);
		}
	}
}
