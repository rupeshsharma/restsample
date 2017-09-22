package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.my.sample.config.security.Authorities;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.data.ChangePasswordData;
import com.my.sample.data.UserData;
import com.my.sample.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();

		if (userService == null) {
			throw new IllegalArgumentException("userService cannot be null");
		}

		this.userService = userService;
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getUser() throws Exception {
		return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserData userData) throws Exception {
		return new ResponseEntity<>(userService.createUser(userData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserData userData) throws Exception {
		return new ResponseEntity<>(userService.updateUser(userData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN, Authorities.ROLE_USER })
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordData changePasswordData) throws Exception {
		userService.changePassword(changePasswordData);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeUser(@PathVariable Long id) throws Exception {
		userService.removeUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
