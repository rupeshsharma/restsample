package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.MasterEntity;

public interface MasterEntityRepository extends JpaRepository<MasterEntity, Long>{

    @Query("Select m FROM MasterEntity m WHERE m.status = 'y'")
    List<MasterEntity> findAll();
    
    @Query("Select m FROM MasterEntity m WHERE m.name = :type AND m.value = :value AND m.status = 'y'")
    MasterEntity findMasterEntityByTypeAndValue(@Param("type")type, @Param("value")value);
}
