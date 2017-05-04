package com.visionet.repair.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springside.modules.mapper.JsonMapper;

import com.visionet.repair.common.constant.BusinessStatus;
import com.visionet.repair.common.constant.ConstantKey;


public class RestException extends RuntimeException {
	
	private static final long serialVersionUID = -1401593546385403720L;
	
	private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

    protected String diyMessage;

    public String getDiyMessage() {
        return diyMessage;
    }

	public HttpStatus status;

	public RestException() {
	}

	public RestException(HttpStatus status) {
		this.status = status;
	}

	public RestException(HttpStatus status, String message) {
		super(message);
		this.status = status;
        this.diyMessage = message;
	}
	
	/**
	 * messageMap.put("code","202");
	 * messageMap.put("msg","XXXXXXXXXX");
	 * status 此处禁止用200，否则手机端不错异常处理
	 * @param status
	 * @param messageMap
	 */
	public RestException(HttpStatus status, Map<String,String> messageMap) {
		super(mapper.toJson(messageMap));
		this.status = status;
	}
	/**
	 * 默认stauts:202, 表示服务器已接受请求，但尚未成功处理
	 * @param messageMap
	 */
	public RestException (Map<String,String> messageMap) {
		super(mapper.toJson(chkMessageMap(messageMap)));
		this.status = HttpStatus.ACCEPTED;
        this.diyMessage = messageMap.get(ConstantKey.MSG);
	}
	
	public RestException(String message) {
		super(mapper.toJson(getMessageMap(message)));
		this.status = HttpStatus.ACCEPTED;
	}
	
	private static Map<String,String> chkMessageMap(Map<String,String> messageMap){
		if(messageMap.size()==1 && messageMap.get(ConstantKey.CODE)==null){
			messageMap.put(ConstantKey.CODE, BusinessStatus.ERROR);
		}
		return messageMap;
	}
	
	private static Map<String,String> getMessageMap(String message){
		Map<String,String> messageMap = new HashMap<String,String>();
		messageMap.put(ConstantKey.CODE, BusinessStatus.ERROR);
		messageMap.put(ConstantKey.MSG,message);
		return messageMap;
	}
}
