package com.trailfinder.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trailfinder.app.ws.io.entity.HikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;

@Repository
public interface HikesRepository extends CrudRepository<HikesEntity, Long> {
	
	List<HikesEntity> findAllByProfileDetails(ProfileEntity profileEntity);
	
	HikesEntity findByHikeId(String hikeId);
	
    @Override
    List<HikesEntity> findAll();
    
}
