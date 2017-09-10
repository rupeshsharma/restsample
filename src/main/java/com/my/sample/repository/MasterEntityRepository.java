package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.sample.domain.MasterEntity;

public interface MasterEntityRepository extends JpaRepository<MasterEntity, Long>{

}
