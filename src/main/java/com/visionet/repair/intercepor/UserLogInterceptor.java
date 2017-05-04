package com.visionet.repair.intercepor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.visionet.repair.controller.BaseController;
import com.visionet.repair.service.account.ShiroDbRealm;

public class UserLogInterceptor extends HandlerInterceptorAdapter {
	
    private static Logger _log = LoggerFactory.getLogger(UserLogInterceptor.class);

	/**
	 * 预处理,Controller之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();

//		if (uri.contains("/console/") && !BaseController.hasRole(Lists.newArrayList(
//				SysConstants.ADMIN, SysConstants.ADMINISTRATOR,SysConstants.AGENT))) {
//			if (!SysConstants.ADMIN.equals((String) request.getAttribute(SysConstants.CONSOLE_FLAG))) {
//				throw new UnauthorizedException(BusinessStatus.USERDENY);
//			}
//		}
		if (!uri.contains("404") && BaseController.getCurrentUserId() == null)
			return true;

		if (uri.startsWith("/kefu/")) {
			uri = uri.substring(5);
		}
		if (uri.equals("/") || uri.startsWith("/frame/")) {
			return true;
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 生成视图之前,调用了Service并返回ModelAndView，但未进行页面渲染
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 最后执行，可用于释放资源 可以根据ex是否为null判断是否发生了异常，进行日志记录
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        String uri = request.getRequestURI();
        ShiroDbRealm.ShiroUser user = null;
        if(!uri.endsWith("404.html")) {
            try {
                user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();

            } catch (Exception e) {
                _log.error("---uri=" + uri);
                _log.error("afterCompletion ShiroUser error:", e.toString());
            }
            if (user == null && !uri.contains("/weblogin")) {
                response.setHeader("sessionInvalid", "true");
            }
        }

		super.afterCompletion(request, response, handler, ex);
	}
}
