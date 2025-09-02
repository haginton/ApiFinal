package com.ada.sql.sql.service.user;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    //@Qualifier("userRepositoryImpl")
    //@Qualifier("userMongoRepositoryImpl")
    private UserRepository repository;

    @Override
    public List<UserGenericDto> getAllUsers() {
        return repository.getAllUsers();
    }

    @Override
    public Optional<UserGenericDto> findUserById(String idUser) {
        return repository.findUserById(idUser);
    }

    @Override
    public Optional<UserGenericDto> createUser(UserGenericDto user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserGenericDto> updateUser(String idUser, UserGenericDto user) {
        user.setIdUser(idUser);
        return repository.save(user);
    }

    @Override
    public Boolean deleteById(String idUser) {
        return repository.deleteById(idUser);
    }

    @Override
    public Optional<UserGenericDto> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
