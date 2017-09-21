package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.data.UserData;
import com.my.sample.service.UserService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	private final UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		super();

		if (userService == null) {
			throw new IllegalArgumentException("userService cannot be null");
		}

		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> getUser(@RequestBody UserData userData) throws Exception {
		return new ResponseEntity<>(userService.authenticate(userData.getUserName(), userData.getPassword()), HttpStatus.OK);
	}

}
