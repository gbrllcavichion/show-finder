package com.api.show_finder.api.dto;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findByUserId(String userId);
}
