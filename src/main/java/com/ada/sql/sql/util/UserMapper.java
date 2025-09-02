package com.ada.sql.sql.util;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.dto.user.UserRequestDto;
import com.ada.sql.sql.dto.user.UserResponseDto;
import com.ada.sql.sql.model.RoleEnum;
import com.ada.sql.sql.model.mongo.UserMongo;
import com.ada.sql.sql.model.sql.Rol;
import com.ada.sql.sql.model.sql.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserGenericDto toEntity(UserRequestDto dto){
        return new UserGenericDto(dto.getUsername(), dto.getPassword(), dto.getEmail());
    }

    public static UserResponseDto toDto(UserGenericDto user){
        return new UserResponseDto(user.getIdUser(), user.getUsername(), user.getEmail(), user.getDateCreation());
    }

    ///
    public static UserGenericDto convertUserJpaToUserGenericDto(User user){

        List<RoleEnum> roleEnums = user.getRoles().stream()
                .map(rol -> RoleEnum.valueOf(rol.getRol()))  // Asegúrate de que el método getRol() retorne el nombre correcto del rol
                .collect(Collectors.toList());

        return new UserGenericDto(
                String.valueOf(user.getIdUser()),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getDateCreation(),
                user.getDateUpdate(),
                roleEnums
        );
    }

    public static User convertUserGenericDtoToUserJpa(UserGenericDto user){
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public static User convertUserGenericDtoToUserJpaWithRole(UserGenericDto user){
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles().stream().map(roleEnum -> new Rol(1L, roleEnum.name())).toList()
        );
    }

    public static UserGenericDto convertUserMongoToUserGenericDto(UserMongo user){
        return new UserGenericDto(
                user.getIdUser(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getDateCreation(),
                user.getDateUpdate(),
                user.getRoles()
        );
    }

    public static UserMongo convertUserGenericDtoToUserMongo(UserGenericDto user){
        return new UserMongo(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }
}
