package com.ada.sql.sql.service.user;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserGenericDto> getAllUsers();
    Optional<UserGenericDto> findUserById(String idUser);
    Optional<UserGenericDto> createUser(UserGenericDto user);
    Optional<UserGenericDto> updateUser(String idUser, UserGenericDto user);
    Boolean deleteById(String idUser);
    Optional<UserGenericDto> findByEmail(String email);
}
