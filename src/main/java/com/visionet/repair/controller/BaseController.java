package com.visionet.repair.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springside.modules.mapper.JsonMapper;

import com.google.common.collect.Maps;
import com.visionet.repair.common.constant.BusinessStatus;
import com.visionet.repair.common.constant.ConstantKey;
import com.visionet.repair.common.exception.RestException;
import com.visionet.repair.common.props.PropsKeys;
import com.visionet.repair.common.props.PropsUtil;
import com.visionet.repair.common.utils.GetterUtil;
import com.visionet.repair.common.utils.PageInfo;
import com.visionet.repair.service.account.ShiroDbRealm.ShiroUser;

public class BaseController {

    public final static String DefaultLocale = GetterUtil.getString(PropsUtil.getProperty(PropsKeys.DEFAULT_LOCALE), "zh");

    public static final int PAGE_SIZE = 10;
	
	protected JsonMapper mapper = new JsonMapper();
	
	public static Map<String,String> getSuccMap(){
		Map<String,String> map = new HashMap<String,String>();
		map.put(ConstantKey.CODE, BusinessStatus.OK);
		return map;
	}

	public static Map<String,String> getSuccMap(String msg){
		Map<String,String> map = new HashMap<String,String>();
		map.put(ConstantKey.CODE, BusinessStatus.OK);
		map.put(ConstantKey.MSG, msg);
		return map;
	}

    public static void setLocale(String locale) {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user==null) return;
        user.locale = locale;
    }

    public static String getLocale() {
            return DefaultLocale;
    }
	
	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	public static String getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		if(user==null) return null;
		return user.userId;
	}
	
	protected void writer(HttpServletResponse res,String obj) throws Exception {
		res.setCharacterEncoding("UTF-8"); 
		PrintWriter pw=null;
		try {
			pw = res.getWriter();
			pw.print(obj);
		} catch (Exception e) {
			throw e;
		}finally{
			if(null!=pw){
				pw.flush();
				pw.close();
			}
		}
	}
	
    public String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    protected static void throwException(String msg) {
        Map<String,String> result = new HashMap<String,String>();
        result.put("code", BusinessStatus.ERROR);
        result.put("msg", msg);
        throw new RestException(result);
    }

	protected static void throwException(String code,String msg) {
		Map<String,String> result = Maps.newHashMap();
        result.put("code", code);
		result.put("msg", msg);
		throw new RestException(result);
	}
	
	protected static <T> Page<T> getPageByList(PageInfo pageInfo,List<T> volist,Class<T> destinationClass){
		if(volist == null || pageInfo == null){
			return null;
		}
		
		if(volist.isEmpty()){
			return new PageImpl<T>(volist, new PageRequest(
					pageInfo.getCurrentPageNumber(), 0, new Sort(
							pageInfo.getSortType(), pageInfo.getSortColumn())),
					0);
		}else{
			return new PageImpl<T>(volist, new PageRequest(
					pageInfo.getCurrentPageNumber(), pageInfo.getPageSize(), new Sort(
							pageInfo.getSortType(), pageInfo.getSortColumn())),
					pageInfo.getTotalCount());
		}
		
	}
	
	protected static <T> Page<T> getPageByList(Page<?> page,List<T> volist,Class<T> destinationClass){
		return new PageImpl<T>(volist, new PageRequest(page.getNumber(),
				page.getSize(), page.getSort()), page.getTotalElements());
	}
}
