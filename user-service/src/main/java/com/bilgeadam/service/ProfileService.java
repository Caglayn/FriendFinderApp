package com.bilgeadam.service;

import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.mapper.Profilemapper;
import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final IProfileRepository profileRepository;
    private final Profilemapper profilemapper;

    public ProfileService(IProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
        this.profilemapper = Mappers.getMapper(Profilemapper.class);
    }

    public String save(ProfileRequestDto dto){
        Profile profile = profilemapper.toProfile(dto);
        profileRepository.save(profile);
        return profile.getId();
    }

    public void update(Profile profile){
        profileRepository.save(profile);
    }

    public void delete(Profile profile){
        profileRepository.delete(profile);
    }

    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

    public Optional<Profile> findByAuthId(long id){
        return profileRepository.findByAuthid(id);
    }

    public Optional<Profile> findById(String id){
        return profileRepository.findById(id);
    }

}
