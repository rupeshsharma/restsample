package com.my.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.config.exception.AccessDeniedException;
import com.my.sample.converter.UserConverter;
import com.my.sample.data.ChangePasswordData;
import com.my.sample.data.UserData;
import com.my.sample.domain.User;
import com.my.sample.repository.UserRepository;
import com.my.sample.service.UserService;

@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		if (userRepository == null) {
			throw new IllegalArgumentException("userRepository cannot be null");
		}
		this.userRepository = userRepository;
	}

	@Override
	public UserData createUser(UserData userData) {
		return null;
	}

	@Override
	public List<UserData> getUser() {
		return null;
	}

	@Override
	public UserData updateUser(UserData userData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(ChangePasswordData changePasswordData) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserData authenticate(String username, String password) {
		UserData userData = null;
		User user = userRepository.authenticate(username, password);
		if (user != null) {
			userData = new UserData();
			UserConverter.convert(user, userData);
		}else{
			throw new AccessDeniedException(401, "User does not have required permission");
		}
		return userData;
	}

}
