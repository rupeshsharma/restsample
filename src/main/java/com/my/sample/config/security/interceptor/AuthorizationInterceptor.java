package com.my.sample.config.security.interceptor;

import java.lang.reflect.Method;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.sample.config.exception.AccessDeniedException;
import com.my.sample.config.security.RequestContext;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.data.UserData;
import com.my.sample.service.UserService;
import com.my.sample.util.AppConstants;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		if (method.isAnnotationPresent(RestSecurity.class)) {
			UserData user = this.authenticateUser(request.getHeader(AppConstants.Headers.AUTHORIZATION));
			if (!this.isAuthorised(user.getRole(), method.getAnnotation(RestSecurity.class).authority())) {
				throw new AccessDeniedException(401, "User does not have required permission");
			}
			RequestContext.setUserContext(user);
		}
		return true;
	}

	private boolean isAuthorised(String userRole, String[] authorities) {
		for (String auth : authorities) {
			if (auth.equals(userRole)) {
				return true;
			}
		}
		return false;
	}

	private UserData authenticateUser(String auth) {
		UserData userData = null;
		if (!StringUtils.isEmpty(auth)) {
			String basicAuth = new String(Base64.getDecoder().decode(auth));
			if (basicAuth.contains(":")) {
				String[] usernameAndPassword = basicAuth.split(":");
				if (usernameAndPassword.length == 2) {
					userData = userService.authenticate(usernameAndPassword[0], usernameAndPassword[1]);
				}
			}
		}
		return userData;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		RequestContext.clearContext();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
