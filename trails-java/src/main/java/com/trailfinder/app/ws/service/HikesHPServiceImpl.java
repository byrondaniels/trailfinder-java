package com.trailfinder.app.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.HPHikesEntity;
import com.trailfinder.app.ws.io.entity.HikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;
import com.trailfinder.app.ws.io.repositories.HPHikesRepository;
import com.trailfinder.app.ws.io.repositories.HikesRepository;
import com.trailfinder.app.ws.io.repositories.ProfileRepository;
import com.trailfinder.app.ws.shared.dto.HikesDto;
import com.trailfinder.app.ws.shared.dto.HikesHPDto;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;

@Service
public class HikesHPServiceImpl implements ProfileHelperService<HikesHPDto> {

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	HPHikesRepository hPHikesRepository;
	
	@Override
	public List<HikesHPDto> getItems(String userId) {
		
        List<HikesHPDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        
        ProfileEntity profileEntity = profileRepository.findByUser(userId);

		if (profileEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
 
        Iterable<HPHikesEntity> hikes = hPHikesRepository.findAllByProfileDetails(profileEntity);
        if(hikes==null) return returnValue;
        
        for(HPHikesEntity hikesEntity:hikes)
            returnValue.add( modelMapper.map(hikesEntity, HikesHPDto.class) );
        
        return returnValue;
	}

	@Override
	public HikesHPDto getItem(String hikeId) {
		
		HikesHPDto returnValue = null;

		HPHikesEntity hikeEntity = hPHikesRepository.findByHPHikeId(hikeId);
        
		if (hikeEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            
		returnValue = new ModelMapper().map(hikeEntity, HikesHPDto.class);
 
        return returnValue;
	}
	
	@Transactional
	@Override
	public void deleteItem(String hikeId) {
		
		HPHikesEntity hPHikeEntity = hPHikesRepository.findByHPHikeId(hikeId);

		if (hPHikeEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		hPHikesRepository.delete(hPHikeEntity);
	}

}

