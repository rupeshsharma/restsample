package com.my.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.config.security.SecurityUserContext;
import com.my.sample.data.MasterEntityData;
import com.my.sample.domain.MasterEntity;
import com.my.sample.domain.User;
import com.my.sample.repository.MasterEntityRepository;
import com.my.sample.service.MasterEntityService;

@Service("masterEntityService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MasterEntityServiceImpl implements MasterEntityService {

	private final MasterEntityRepository masterEntityRepository;
	private final SecurityUserContext securityUserContext;

	@Autowired
	public MasterEntityServiceImpl(MasterEntityRepository masterEntityRepository,
			SecurityUserContext securityUserContext) {
		super();

		if (masterEntityRepository == null) {
			throw new IllegalArgumentException("masterEntityRepository cannot be null");
		}
		if (securityUserContext == null) {
			throw new IllegalArgumentException("securityUserContext cannot be null");
		}
		this.masterEntityRepository = masterEntityRepository;
		this.securityUserContext = securityUserContext;
	}

	@Override
	public MasterEntityData getMasterEntity() {
		MasterEntityData masterEntityData = new MasterEntityData();
		List<MasterEntity> masterEntityList = masterEntityRepository.findAll();
		String masterEntityName = null;
		for (MasterEntity masterEntity : masterEntityList) {
			masterEntityName = masterEntity.getName();
			List<String> values = masterEntityData.getMasterData().get(masterEntityName);
			if (values != null) {
				values.add(masterEntity.getValue());
			} else {
				values = new ArrayList<String>();
				values.add(masterEntity.getValue());
			}
			masterEntityData.getMasterData().put(masterEntityName, values);
		}
		return masterEntityData;
	}

	private MasterEntity findMasterEntityByTypeAndValue(String type, String value) {
		return masterEntityRepository.findMasterEntityByTypeAndValue(type, value);
	}

	@Override
	public void addMasterEntity(String type, String value) {
		MasterEntity masterEntity = new MasterEntity();
		masterEntity.setName(type);
		masterEntity.setValue(value);
		masterEntity.setStatus('y');
		masterEntity.setCreatedBy(new User(securityUserContext.getCurrentUser().getId()));
		masterEntityRepository.save(masterEntity);
	}

	@Override
	public void updateMasterEntity(String type, String previousValue, String newValue) {
		MasterEntity masterEntity = findMasterEntityByTypeAndValue(type, previousValue);
		masterEntity.setValue(newValue);
		masterEntityRepository.save(masterEntity);
	}

	@Override
	public void removeMasterEntity(String type, String value) {
		MasterEntity masterEntity = findMasterEntityByTypeAndValue(type, value);
		masterEntity.setStatus('n');
		masterEntityRepository.save(masterEntity);
	}

}
