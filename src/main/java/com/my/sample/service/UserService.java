package com.my.sample.service;

import java.util.List;

import com.my.sample.data.ChangePasswordData;
import com.my.sample.data.UserData;


public interface UserService {
	UserData createUser(UserData userData);

	List<UserData> getUser();

	UserData updateUser(UserData userData);
	
	void changePassword(ChangePasswordData changePasswordData);

}
