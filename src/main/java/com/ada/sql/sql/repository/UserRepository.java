package com.ada.sql.sql.repository;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<UserGenericDto> getAllUsers();
    Optional<UserGenericDto> findUserById(String idUser);
    Optional<UserGenericDto> save(UserGenericDto user);
    Boolean deleteById(String idUser);
    Optional<UserGenericDto> findByEmail(String email);
}
