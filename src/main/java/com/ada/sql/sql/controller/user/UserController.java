package com.ada.sql.sql.controller.user;


import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.dto.user.UserRequestDto;
import com.ada.sql.sql.dto.user.UserResponseDto;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.service.user.UserService;
import com.ada.sql.sql.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        try {
            List<UserResponseDto> listUsers = service.getAllUsers()
                    .stream()
                    .map(user -> UserMapper.toDto(user))
                    .toList();

            if (listUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity("An error has occurred in the server", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id){
        try {
            UserResponseDto userResponseDto = service.findUserById(id).map(userGenericDto -> UserMapper.toDto(userGenericDto)).get();
            return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("User " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto){
        UserGenericDto user = service.createUser(UserMapper.toEntity(dto)).get();
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.CREATED);
        //return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody UserRequestDto dto){
        try {
            UserGenericDto user = service.updateUser(id, UserMapper.toEntity(dto)).get();
            return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity("User " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        Boolean eliminoUsuario = service.deleteById(id);
        return new ResponseEntity<>(eliminoUsuario, HttpStatus.OK);
    }

}
