package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.service.MasterEntityService;

@RestController
@RequestMapping("/api/master")
public class MasterEntityController {

	private final MasterEntityService masterEntityService;

	@Autowired
	public MasterEntityController(MasterEntityService masterEntityService) {
		super();

		if (masterEntityService == null) {
			throw new IllegalArgumentException("masterEntityService cannot be null");
		}

		this.masterEntityService = masterEntityService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMasterEntity() {
		return new ResponseEntity<>(masterEntityService.getMasterEntity(), HttpStatus.OK);
	}

	@RequestMapping(value = "/add/{type}/{value}", method = RequestMethod.POST)
	public ResponseEntity<?> addMasterEntity(@PathVariable String type, @PathVariable String value) {
		masterEntityService.addMasterEntity(type, value);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/update/{type}/{previousValue}/{newValue}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMasterEntity(@PathVariable String type, @PathVariable String previousValue,
			@PathVariable String newValue) {
		masterEntityService.updateMasterEntity(type, previousValue, newValue);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/remove/{type}/{value}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeMasterEntity(@PathVariable String type, @PathVariable String value) {
		masterEntityService.removeMasterEntity(type, value);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
