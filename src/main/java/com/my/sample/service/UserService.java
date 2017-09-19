package com.my.sample.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import com.my.sample.data.UserData;
import com.my.sample.data.ChangePasswordData;


public interface UserService {
	UserData createUser(UserData userData);

	List<UserData> getUser();

	UserData updateUser(UserData userData);
	
	void changePassword(ChangePasswordData changePasswordData);

}
