package com.api.show_finder.domain.repository;

import com.api.show_finder.domain.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findByName(String name);

}
