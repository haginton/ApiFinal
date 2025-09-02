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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    private ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> listUsers = service.getAllUsers()
                .stream()
                .map(user -> UserMapper.toDto(user))
                .toList();

        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable String id){
        UserResponseDto userResponseDto = service.findUserById(id).map(userGenericDto -> UserMapper.toDto(userGenericDto)).get();
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto){
        UserGenericDto user = service.createUser(UserMapper.toEntity(dto)).get();
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.CREATED);
        //return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody UserRequestDto dto){
        UserGenericDto user = service.updateUser(id, UserMapper.toEntity(dto)).get();
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        Boolean eliminoUsuario = service.deleteById(id);
        return new ResponseEntity<>(eliminoUsuario, HttpStatus.OK);
    }

}
