package com.ada.sql.sql.repository.mongo.user;

import com.ada.sql.sql.model.mongo.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserMongo, String> {
    Optional<UserMongo> findByEmail(String email);
}
