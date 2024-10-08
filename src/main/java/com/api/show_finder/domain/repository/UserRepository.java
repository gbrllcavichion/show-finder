package com.api.show_finder.domain.repository;

import com.api.show_finder.domain.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findById(ObjectId id);
    Optional<User> findByEmail(String email);
}
