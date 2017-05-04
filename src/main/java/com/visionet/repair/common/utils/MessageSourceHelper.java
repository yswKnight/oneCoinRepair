package com.visionet.repair.common.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceHelper {
	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        if(locale == null){
            locale = new Locale("zh");
        }
		String msg = messageSource.getMessage(code, args, defaultMessage,locale);
		
		return msg != null ? msg.trim() : msg;
	}
	public String getMessage(String code) {
		String msg = this.getMessage(code,null,"",null);
		
		return msg != null ? msg.trim() : msg;
	}


    public static String GetMessages(String code) {
        String msg = ((MessageSourceHelper) SpringContextUtil.getBean("messageSourceHelper")).getMessage(code);

        return msg != null ? msg.trim() : msg;
    }
    public static String GetMessages(String code,Object[] args) {
        String msg = ((MessageSourceHelper)SpringContextUtil.getBean("messageSourceHelper")).getMessage(code, args, "", new Locale(com.visionet.repair.controller.BaseController.getLocale()));

        return msg != null ? msg.trim() : msg;
    }

}
