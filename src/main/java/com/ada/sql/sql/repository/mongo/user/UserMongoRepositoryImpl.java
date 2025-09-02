package com.ada.sql.sql.repository.mongo.user;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.mongo.UserMongo;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.repository.UserRepository;
import com.ada.sql.sql.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongoImpl")
public class UserMongoRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @Override
    public List<UserGenericDto> getAllUsers() {
        return userRepositoryMongo.findAll()
                .stream()
                .map(userMongo -> UserMapper.convertUserMongoToUserGenericDto(userMongo))
                .toList()
                ;
    }

    @Override
    public Optional<UserGenericDto> findUserById(String idUser) {
        return userRepositoryMongo.findById(idUser)
                .map(userMongo -> UserMapper.convertUserMongoToUserGenericDto(userMongo))
                ;
    }

    @Override
    public Optional<UserGenericDto> save(UserGenericDto user) {
        if (user.getIdUser() == null){ //insert
            return Optional.of(UserMapper.convertUserMongoToUserGenericDto(userRepositoryMongo.save(UserMapper.convertUserGenericDtoToUserMongo(user))));
        }else { //update
            Optional<UserGenericDto> userFound = findUserById(user.getIdUser());
            if (userFound.isPresent()){
                UserMongo userMongoFound = UserMapper.convertUserGenericDtoToUserMongo(userFound.get());
                userMongoFound.setIdUser(user.getIdUser());
                userMongoFound.setDateCreation(userFound.get().getDateCreation());
                userMongoFound.updateUser(UserMapper.convertUserGenericDtoToUserMongo(user));
                return Optional.of(UserMapper.convertUserMongoToUserGenericDto(userRepositoryMongo.save(userMongoFound)));
            }
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteById(String idUser) {
        Optional<UserGenericDto> userFound = findUserById(idUser);
        if (userFound.isPresent()){
            userRepositoryMongo.deleteById(idUser);
            return true;
        }
        return true;
    }

    @Override
    public Optional<UserGenericDto> findByEmail(String email) {
        Optional<UserMongo> userMongoFound = userRepositoryMongo.findByEmail(email);
        if (userMongoFound.isPresent()){
            return Optional.of(UserMapper.convertUserMongoToUserGenericDto(userMongoFound.get()));
        }
        return Optional.empty();
    }
}
