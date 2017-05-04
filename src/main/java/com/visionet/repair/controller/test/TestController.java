package com.visionet.repair.controller.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.visionet.repair.common.poi.MyExcelUtils;
import com.visionet.repair.common.utils.BeanConvertMap;
import com.visionet.repair.common.utils.Validator;
import com.visionet.repair.controller.BaseController;
import com.visionet.repair.entity.account.User;
import com.visionet.repair.entity.test.Test;
import com.visionet.repair.repository.test.TestDao;
import com.visionet.repair.service.account.UserService;
import com.visionet.repair.vo.account.UserVo;

@Controller
public class TestController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TestDao testDao;
	
	@RequestMapping(value = "/testExcel", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testExcel() throws Exception {
		List<Test> testList = new ArrayList<Test>();
		File file = new File("C:\\Users\\Administrator\\Desktop\\TestImport.xlsx");	
		InputStream is = new FileInputStream(file); 
		MyExcelUtils utils = new MyExcelUtils(); 
		String sheetName = utils.readSheetName(is);
		String [][] result = new  String[800][9];
		Map<Integer, List<String>> values = utils.readExcelContent(is);
		for (int i = 0; i < values.size(); i++) {
			List<String> list = values.get(i);
			if(list!=null&&list.size()>0){
				for (int j = 0; j < list.size(); j++) {
					result[i][j] = list.get(j);
				}
			}
		}
		int startRow=1;
		int startColumn=0;
		while((startColumn+12)<result.length){
			int i = startRow;
			for (; i <startRow+12; ++i) {
				int j =startColumn;
				for (; j <startColumn+3; ++j) {
					Test t = new Test();
					t.setSheetName(sheetName);
					testList.add(t);
					if((i==(startRow+1))&&(j==2||j==5||j==8)){
						t.setColorName(result[i][j]);
					}else if((i==(startRow+1))&&(j==startColumn)){
						t.setTitleName(result[i][j]);
					}else if(i!=startRow&&i!=(startRow+1)){
						if(Validator.isNotNull(result[i][startColumn])&&Validator.isNotNull(result[i][startColumn+1])&&Validator.isNotNull(result[i][startColumn+2])){
							t.setClo1(result[i][startColumn]);
							t.setClo2(result[i][startColumn+1]);
							t.setClo3(result[i][startColumn+2]);
							System.out.println(t.getSheetName()+"  "+t.getTitleName()+"  "+t.getClo1()+"   "+t.getClo2()+"   "+t.getClo3());
						}
					}
//					if(Validator.isNotNull(result[i][j])){
//						System.out.print(result[i][j]+"  ");
//					}
				}
//				System.out.println();
			}
			startColumn+=3;   //读完第一个单元格，列数+3，行数不变
			if(startColumn==9){  //读完第一排三个单元格，列数为0，行数加12
				startRow+=12;
				startColumn=0;
			}
		}
			
		return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/testSave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testSave(@RequestBody UserVo userVo) throws Exception {
		if(userService.saveUser(userVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("添加失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testUpdate(@RequestBody UserVo userVo) throws Exception {
		if(userService.updateUser(userVo)){
			return new ResponseEntity<Map<String,String>>(getSuccMap(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("修改失败", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/testList", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> testList(@RequestBody UserVo userVo) throws Exception {
		Page<User> page = userService.listUser(userVo);
        List<UserVo> listVo = BeanConvertMap.mapList(page.getContent(), UserVo.class);
        return new ResponseEntity<Page<UserVo>>(getPageByList(page, listVo, UserVo.class), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/testDelete/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> testDelete(@PathVariable String userId) throws Exception {
		if(userService.deleteUser(userId)){
			return new ResponseEntity<Map<String,String>>(getSuccMap("删除成功"), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("删除失败", HttpStatus.OK);
		}
	}
}
