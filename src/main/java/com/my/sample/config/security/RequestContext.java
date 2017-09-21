package com.my.sample.config.security;

import com.my.sample.data.UserData;

final public class RequestContext {

	private static final ThreadLocal<UserData> contextHolder = new ThreadLocal<UserData>();

	public static void clearContext() {
		contextHolder.remove();
	}

	public static void setUserContext(UserData info) {
		contextHolder.set(info);
	}

	public static UserData getUserContext() {
		return contextHolder.get();
	}

}
