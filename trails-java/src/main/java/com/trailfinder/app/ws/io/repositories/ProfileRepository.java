package com.trailfinder.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.trailfinder.app.ws.io.entity.ProfileEntity;


public interface ProfileRepository extends CrudRepository<ProfileEntity,Long> {
	
	@Transactional
	void deleteByUser(String id);
	
    @Override
    List<ProfileEntity> findAll();
    
	ProfileEntity findByUser(String userId);
    
}
