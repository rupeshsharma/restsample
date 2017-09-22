package com.my.sample.service;

import java.util.List;

import javax.naming.AuthenticationException;

import com.my.sample.data.ChangePasswordData;
import com.my.sample.data.UserData;
import com.my.sample.domain.User;


public interface UserService {
	UserData createUser(UserData userData);

	List<UserData> getUser();

	UserData updateUser(UserData userData);
	
	void changePassword(ChangePasswordData changePasswordData);
	
	void removeUser(Long id);

	UserData authenticate(String username, String password) throws AuthenticationException;
	
	User getUserByUsername(String username);

}
