package com.my.sample.converter;

import com.my.sample.data.UserData;
import com.my.sample.domain.User;

public class UserConverter {
	public static void reverse(UserData source, User target){
	}
	
	public static void convert(User source, UserData target){
		target.setId(source.getId());
		target.setName(source.getName());
	}
}
