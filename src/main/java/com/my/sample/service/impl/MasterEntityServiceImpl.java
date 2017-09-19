package com.my.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.data.MasterEntityData;
import com.my.sample.domain.MasterEntity;
import com.my.sample.repository.MasterEntityRepository;
import com.my.sample.service.MasterEntityService;

@Service("masterEntityService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MasterEntityServiceImpl implements MasterEntityService {

	private final MasterEntityRepository masterEntityRepository;

	@Autowired
	public MasterEntityServiceImpl(MasterEntityRepository masterEntityRepository) {
		super();

		if (masterEntityRepository == null) {
			throw new IllegalArgumentException("masterEntityRepository cannot be null");
		}
		this.masterEntityRepository = masterEntityRepository;
	}

	@Override
	public MasterEntityData getMasterEntity() {
		MasterEntityData masterEntityData = new MasterEntityData();
		List<MasterEntity> masterEntityList = masterEntityRepository.findAll();
		String masterEntityName = null;
		for(MasterEntity masterEntity : masterEntityList){
			masterEntityName = masterEntity.getName();
			List<String> values = masterEntityData.getMasterData().get(masterEntityName);
			if(values!=null){
				values.add(masterEntity.getValue());
			}else{
				values = new ArrayList<String>();
				values.add(masterEntity.getValue());
			}
			masterEntityData.getMasterData().put(masterEntityName, values);
		}
		return masterEntityData;
	}

}