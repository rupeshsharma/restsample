package com.my.sample.REST.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMenu() throws Exception {
		return new ResponseEntity<>("",
				HttpStatus.OK);
	}

}
