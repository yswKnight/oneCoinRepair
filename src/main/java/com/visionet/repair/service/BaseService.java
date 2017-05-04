package com.visionet.repair.service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.util.HtmlUtils;

import com.visionet.repair.common.exception.RestException;


public abstract class BaseService {
	
	public static final String LOGO_REGX = "(imglogo)";
	
	/**
	 * 创建分页请求.
	 */
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)||"id".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if(sortType!=null) {
			sort = new Sort(Direction.ASC, sortType);
		}
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}


    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType,Direction direction) {

        Sort sort = null;
        if ("auto".equals(sortType)) {
            sort = new Sort(direction, "id");
        } else if(sortType!=null) {
            sort = new Sort(direction, sortType);
        }

        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }

	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType,String directionStr) {
		Direction direction = Direction.DESC;
		if(directionStr!=null&&"asc".equals(directionStr.toLowerCase())){
			direction = Direction.ASC;
		}
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(direction, "id");
		} else if(sortType!=null) {
			sort = new Sort(direction, sortType);
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}
	
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize, List<Order> orders) {
		Sort sort = new Sort(orders);
	
		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	protected void throwException(String code,String msg){
		Map<String,String> response = new HashMap<String,String>();
		response.put("code", code);
		response.put("msg", msg);
		throw new RestException(response);
	}
	
}
