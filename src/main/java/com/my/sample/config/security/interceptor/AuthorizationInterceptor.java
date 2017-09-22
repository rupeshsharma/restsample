package com.my.sample.config.security.interceptor;

import java.lang.reflect.Method;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.sample.config.exception.AccessDeniedException;
import com.my.sample.config.security.BCryptPasswordEncoder;
import com.my.sample.config.security.RequestContext;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.converter.UserConverter;
import com.my.sample.data.UserData;
import com.my.sample.domain.User;
import com.my.sample.service.UserService;
import com.my.sample.util.AppConstants;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		// Authorize
		if (method.isAnnotationPresent(RestSecurity.class)) {
			UserData user = this.authenticateUser(request.getHeader(AppConstants.Headers.AUTHORIZATION));
			if (user == null
					|| !this.isAuthorised(user.getRole(), method.getAnnotation(RestSecurity.class).authority())) {
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
					User user = userService.getUserByUsername(usernameAndPassword[0]);
					if (user != null && bCryptPasswordEncoder.matches(usernameAndPassword[1], user.getPassword())) {
						userData = new UserData();
						UserConverter.convert(user, userData);
					}
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

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
}
