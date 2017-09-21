package com.my.sample.config.security;

import org.springframework.stereotype.Component;

import com.my.sample.data.UserData;

@Component
public final class SecurityUserContext {

	public final UserData getCurrentUser() {
		UserData authUser = RequestContext.getUserContext();
		return authUser;
	}
}
