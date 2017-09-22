package com.my.sample.service.impl;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.config.security.BCryptPasswordEncoder;
import com.my.sample.config.security.SecurityUserContext;
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
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final SecurityUserContext securityUserContext;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			SecurityUserContext securityUserContext) {
		super();
		if (userRepository == null) {
			throw new IllegalArgumentException("userRepository cannot be null");
		}
		if (bCryptPasswordEncoder == null) {
			throw new IllegalArgumentException("bCryptPasswordEncoder cannot be null");
		}
		if (securityUserContext == null) {
			throw new IllegalArgumentException("securityUserContext cannot be null");
		}
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.securityUserContext = securityUserContext;
	}

	@Override
	public UserData createUser(UserData userData) {
		User user = new User();
		UserConverter.reverse(userData, user);
		user.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
		user.setStatus('y');
		user.setCreatedBy(new User(securityUserContext.getCurrentUser().getId()));
		user = userRepository.save(user);
		UserConverter.convert(user, userData);
		return userData;
	}

	@Override
	public List<UserData> getUser() {
		return userRepository.getALL();
	}

	@Override
	public UserData updateUser(UserData userData) {
		User user = userRepository.findOne(userData.getId());
		user.setName(userData.getName());
		user.setEmail(userData.getEmail());
		user.setContact(userData.getContact());
		user.setRole(userData.getRole());
		user = userRepository.save(user);
		UserConverter.convert(user, userData);
		return userData;
	}

	@Override
	public void changePassword(ChangePasswordData changePasswordData) {
		User user = userRepository.findOne(changePasswordData.getId());
		user.setPassword(bCryptPasswordEncoder.encode(changePasswordData.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void removeUser(Long id) {
		User user = userRepository.findOne(id);
		user.setStatus('n');
		userRepository.save(user);
	}

	@Override
	public UserData authenticate(String username, String password) throws AuthenticationException {
		UserData userData = null;
		User user = getUserByUsername(username);
		if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
			userData = new UserData();
			UserConverter.convert(user, userData);
		} else {
			throw new AuthenticationException("Incorrect username and password");
		}
		return userData;
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

}
