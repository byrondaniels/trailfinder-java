package com.trailfinder.app.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trailfinder.app.ws.exceptions.UserServiceException;
import com.trailfinder.app.ws.io.entity.HikesEntity;
import com.trailfinder.app.ws.io.entity.ProfileEntity;
import com.trailfinder.app.ws.io.repositories.HikesRepository;
import com.trailfinder.app.ws.io.repositories.ProfileRepository;
import com.trailfinder.app.ws.shared.dto.HikesDto;
import com.trailfinder.app.ws.ui.model.response.ErrorMessages;

@Service
public class ProfileHelperServiceImpl implements ProfileHelperService<HikesDto> {

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	HikesRepository hikesRepository;
	
	@Override
	public List<HikesDto> getItems(String userId) {
		
        List<HikesDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        
        ProfileEntity profileEntity = profileRepository.findByUser(userId);

		if (profileEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
 
        Iterable<HikesEntity> hikes = hikesRepository.findAllByProfileDetails(profileEntity);
        if(hikes==null) return returnValue;
        
        for(HikesEntity hikesEntity:hikes)
            returnValue.add( modelMapper.map(hikesEntity, HikesDto.class) );
        
        return returnValue;
	}

	@Override
	public HikesDto getItem(String hikeId) {
		
		HikesDto returnValue = null;

		HikesEntity hikeEntity = hikesRepository.findByHikeId(hikeId);
        
		if (hikeEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            
		returnValue = new ModelMapper().map(hikeEntity, HikesDto.class);
 
        return returnValue;
	}
	
	@Transactional
	@Override
	public void deleteItem(String hikeId) {
		
		HikesEntity hikeEntity = hikesRepository.findByHikeId(hikeId);

		if (hikeEntity == null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		hikesRepository.delete(hikeEntity);
	}

}
