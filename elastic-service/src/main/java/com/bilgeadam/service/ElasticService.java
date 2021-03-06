package com.bilgeadam.service;

import com.bilgeadam.repository.IProfileRepository;
import com.bilgeadam.repository.entity.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElasticService {
    private final IProfileRepository repository;

    public void save(Profile profile){
        repository.save(profile);
    }

    public void update(Profile profile){
        repository.save(profile);
    }

    public void delete(Profile profile){
        repository.delete(profile);
    }

    public Optional<Profile> findById(String id){
        return repository.findById(id);
    }

    public List<Profile> findByFirstNameLike(String firstName) {
        return repository.findByFirstnameLike(firstName);
    }

}
