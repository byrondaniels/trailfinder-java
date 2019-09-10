package com.trailfinder.app.ws.io.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.trailfinder.app.ws.io.entity.HPHikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;

@Repository
public interface HPHikesRepository extends CrudRepository<HPHikesEntity, Long> {
	
	List<HPHikesEntity> findAllByProfileDetails(ProfileEntity profileEntity);
	
	HPHikesEntity findByHPHikeId(String addressId);
	
    @Override
    List<HPHikesEntity> findAll();
    
}
