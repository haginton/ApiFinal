package com.ada.sql.sql.repository.postgresql.user;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.RoleEnum;
import com.ada.sql.sql.model.sql.Rol;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.repository.UserRepository;
import com.ada.sql.sql.repository.postgresql.rol.RolRepositoryJpa;
import com.ada.sql.sql.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jpaImpl")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Autowired
    private RolRepositoryJpa rolRepositoryJpa;

    @Override
    public List<UserGenericDto> getAllUsers() {
        return userRepositoryJpa.findAll()
                .stream()
                .map(user -> UserMapper.convertUserJpaToUserGenericDto(user))
                .toList();
    }

    @Override
    public Optional<UserGenericDto> findUserById(String idUser) {
        return userRepositoryJpa.findById(Long.parseLong(idUser))
                .map(user -> UserMapper.convertUserJpaToUserGenericDto(user));
    }

    @Override
    public Optional<UserGenericDto> save(UserGenericDto user) {
        if (user.getIdUser() == null){ //save
            Rol rolDefault = rolRepositoryJpa.findById(1L)
                    .orElse(new Rol(1L, "USER"));
            RoleEnum roleEnum = RoleEnum.valueOf(rolDefault.getRol());
            user.setRoles(Arrays.asList(roleEnum));
            return Optional.of(
                    UserMapper.convertUserJpaToUserGenericDto(
                            userRepositoryJpa.save(
                                    UserMapper.convertUserGenericDtoToUserJpaWithRole(user)
                            )
                    )
            );
        }else { //update
            Optional<UserGenericDto> userFound = findUserById(user.getIdUser());
            if (userFound.isPresent()){
                User userJpa = UserMapper.convertUserGenericDtoToUserJpa(userFound.get());
                userJpa.setIdUser(Long.parseLong(user.getIdUser()));
                userJpa.setDateCreation(userFound.get().getDateCreation());
                userJpa.updateUser(UserMapper.convertUserGenericDtoToUserJpa(user));
                return Optional.of(UserMapper.convertUserJpaToUserGenericDto(userRepositoryJpa.save(userJpa)));
            }
            return Optional.empty();
        }
    }

    @Override
    public Boolean deleteById(String idUser) {
        Optional<UserGenericDto> userFound = findUserById(idUser);
        if (userFound.isPresent()){
            userRepositoryJpa.deleteById(Long.parseLong(idUser));
            return true;
        }
        return true;
    }

    @Override
    public Optional<UserGenericDto> findByEmail(String email) {
        Optional<User> userFound = userRepositoryJpa.findByEmail(email);
        if (userFound.isPresent()){
            return Optional.of(UserMapper.convertUserJpaToUserGenericDto(userFound.get()));
        }else{
            return Optional.empty();
        }
    }
}
