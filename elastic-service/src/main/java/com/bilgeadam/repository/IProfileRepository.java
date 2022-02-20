package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Profile;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface IProfileRepository extends ElasticsearchRepository<Profile, String > {

    List<Profile> findByFirstnameLike(String firstname);
}
