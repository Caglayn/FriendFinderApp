package com.bilgeadam.controller;

import static com.bilgeadam.constants.RestApiUrls.*;

import com.bilgeadam.dto.request.FindByAuthIdDto;
import com.bilgeadam.dto.request.IsProfileExistsDto;
import com.bilgeadam.dto.request.ProfileRequestDto;
import com.bilgeadam.rabbitmq.model.ProfileNotification;
import com.bilgeadam.rabbitmq.producer.ElasticProfileProducer;
import com.bilgeadam.repository.entity.Profile;
import com.bilgeadam.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(VERSION+PROFILE)
public class ProfileController {
    private final ProfileService profileService;
    private final ElasticProfileProducer elasticProfileProducer;

    public ProfileController(ProfileService profileService, ElasticProfileProducer elasticProfileProducer) {
        this.profileService = profileService;
        this.elasticProfileProducer = elasticProfileProducer;
    }

    @PostMapping(SAVE)
    public ResponseEntity<String> save(@RequestBody @Valid ProfileRequestDto dto){
        String id = profileService.save(dto);
        elasticProfileProducer.sendMessageProfileSave(ProfileNotification.builder()
                .city(dto.getCity())
                .country(dto.getCountry())
                .email(dto.getEmail())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .profileId(id)
                .build());
        return ResponseEntity.ok(id);
    }

    @PostMapping(FINDBYAUTHID)
    public ResponseEntity<String> findByAuthId(@Valid @RequestBody FindByAuthIdDto dto){
        Optional<Profile> profile = profileService.findByAuthId(dto.getAuthId());
        if (profile.isPresent()){
            return ResponseEntity.ok(profile.get().getId());
        }
        else{
            return ResponseEntity.ok("");
        }
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Profile>> findAll(){
        return ResponseEntity.ok(profileService.findAll());
    }

    @PostMapping(ISPROFILEEXISTBYID)
    public ResponseEntity<Boolean> isProfileExistById(@RequestBody IsProfileExistsDto dto){
        Optional<Profile> profile = profileService.findById(dto.getProfileId());
        if(profile.isPresent()){
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }


}
