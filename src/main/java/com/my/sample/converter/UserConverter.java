package com.my.sample.converter;

import com.my.sample.data.UserData;
import com.my.sample.domain.User;

public class UserConverter {
	public static void reverse(UserData source, User target) {
		target.setName(source.getName());
		target.setContact(source.getContact());
		target.setEmail(source.getEmail());
		target.setRole(source.getRole());
		target.setUserName(source.getUserName());
	}

	public static void convert(User source, UserData target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setContact(source.getContact());
		target.setEmail(source.getEmail());
		target.setRole(source.getRole());
		target.setUserName(source.getUserName());
	}
}
