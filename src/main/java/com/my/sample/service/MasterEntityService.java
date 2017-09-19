package com.my.sample.service;

import com.my.sample.data.MasterEntityData;

public interface MasterEntityService {

	MasterEntityData getMasterEntity();
	void addMasterEntity(String type, String value);
	void updateMasterEntity(String type, String previousValue, String newValue);
	void removeMasterEntity(String type, String value);
	
}
