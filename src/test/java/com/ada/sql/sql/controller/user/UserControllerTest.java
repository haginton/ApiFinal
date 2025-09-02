package com.ada.sql.sql.controller.user;

import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.dto.user.UserRequestDto;
import com.ada.sql.sql.dto.user.UserResponseDto;
import com.ada.sql.sql.model.RoleEnum;
import com.ada.sql.sql.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    //-------------- clase

    @Test
    public void getAllUserTest(){
        // organizar
        List<UserGenericDto> mockUsers = Arrays.asList(
                new UserGenericDto("1", "pepe", "gsfdg22dgsdfsd22tytrgsdgs", "pepe@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.USER)),
                new UserGenericDto("2", "pepe2", "gsfdg22diyuigsdfsd22gsdgs", "pepe2@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.USER, RoleEnum.ADMIN))
        );

        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);

        // actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        // afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        assertEquals("1", response.getBody().get(0).getIdUser());
        assertEquals("pepe", response.getBody().get(0).getUsername());
        assertEquals("pepe@mail.com", response.getBody().get(0).getEmail());

        assertEquals("2", response.getBody().get(1).getIdUser());
        assertEquals("pepe2", response.getBody().get(1).getUsername());
        assertEquals("pepe2@mail.com", response.getBody().get(1).getEmail());

        //Sirve para comprobar que un método en un mock se llamó durante la ejecución del test.
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
    }



    @Test
    public void getByIdTest() {
        // organizar
        UserGenericDto mockUser = new UserGenericDto("1", "pepe", "pass1", "pepe@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.USER));
        Mockito.when(userService.findUserById("1")).thenReturn(Optional.of(mockUser));

        // actuar
        ResponseEntity<UserResponseDto> response = userController.getById("1");

        // afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getIdUser());
        assertEquals("pepe", response.getBody().getUsername());
        assertEquals("pepe@mail.com", response.getBody().getEmail());

        Mockito.verify(userService, Mockito.times(1)).findUserById("1");
    }

    @Test
    public void createUserTest() {
        // organizar
        UserRequestDto request = new UserRequestDto("pepe", "pass1", "pepe@mail.com");
        UserGenericDto createdUser = new UserGenericDto("1", "pepe", "pass1", "pepe@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.USER));

        Mockito.when(userService.createUser(Mockito.any(UserGenericDto.class))).thenReturn(Optional.of(createdUser));

        // actuar
        ResponseEntity<UserResponseDto> response = userController.createUser(request);

        // afirmar
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getIdUser());
        assertEquals("pepe", response.getBody().getUsername());
        assertEquals("pepe@mail.com", response.getBody().getEmail());

        Mockito.verify(userService, Mockito.times(1)).createUser(Mockito.any(UserGenericDto.class));
    }

    @Test
    public void updateUserTest() {
        // organizar
        UserRequestDto request = new UserRequestDto("pepeMod", "pass1", "pepeMod@mail.com");
        UserGenericDto updatedUser = new UserGenericDto("1", "pepeMod", "pass1", "pepeMod@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.ADMIN));

        Mockito.when(userService.updateUser(Mockito.eq("1"), Mockito.any(UserGenericDto.class)))
                .thenReturn(Optional.of(updatedUser));

        // actuar
        ResponseEntity<UserResponseDto> response = userController.updateUser("1", request);

        // afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("1", response.getBody().getIdUser());
        assertEquals("pepeMod", response.getBody().getUsername());
        assertEquals("pepeMod@mail.com", response.getBody().getEmail());

        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.eq("1"), Mockito.any(UserGenericDto.class));
    }

    @Test
    public void updateUserNotFoundTest(){

        // organizar
        String idUserNotFound = "30";

        // organizar
        UserRequestDto request = new UserRequestDto("pepeMod", "pass1", "pepeMod@mail.com");

        Mockito.when(userService.updateUser(Mockito.eq(idUserNotFound), Mockito.any(UserGenericDto.class)))
                .thenThrow(new NoSuchElementException("User " + idUserNotFound + " not found"));

        // actuar
        ResponseEntity<UserResponseDto> response = userController.updateUser(idUserNotFound, request);

        // afirmar
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        Assertions.assertEquals("User " + idUserNotFound + " not found", response.getBody());

        Mockito.verify(userService, Mockito.times(1)).updateUser(Mockito.eq(idUserNotFound), Mockito.any(UserGenericDto.class));
    }

    @Test
    public void deleteUserTest() {
        // organizar
        Mockito.when(userService.deleteById("1")).thenReturn(true);

        // actuar
        ResponseEntity<Boolean> response = userController.delete("1");

        // afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());

        Mockito.verify(userService, Mockito.times(1)).deleteById("1");
    }

    //-------------- practica tdd

    @Test
    public void getAllUserEmptyListTest(){
        // organizar
        List<UserGenericDto> mockUsers = new ArrayList<>();

        Mockito.when(userService.getAllUsers()).thenReturn(mockUsers);

        // actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        // afirmar
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        //Sirve para comprobar que un método en un mock se llamó durante la ejecución del test.
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
    }

    @Test
    public void getAllUserServerErrorTest(){
        // organizar
        List<UserGenericDto> mockUsers = new ArrayList<>();

        Mockito.when(userService.getAllUsers()).thenThrow(new RuntimeException("An error has occurred in the server"));

        // actuar
        ResponseEntity<List<UserResponseDto>> response = userController.getAllUsers();

        // afirmar
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("An error has occurred in the server", response.getBody());
        //Sirve para comprobar que un método en un mock se llamó durante la ejecución del test.
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
    }

    @Test
    public void findUserByIdNotFoundTest(){

        // organizar
        String idUserNotFound = "30";
        UserGenericDto mockUser = new UserGenericDto("1", "pepe", "pass1", "pepe@mail.com", LocalDateTime.now(), LocalDateTime.now(), List.of(RoleEnum.USER));
        Mockito.when(userService.findUserById(idUserNotFound)).thenThrow(new NoSuchElementException("User " + idUserNotFound + " not found"));

        // actuar
        ResponseEntity<UserResponseDto> response = userController.getById(idUserNotFound);

        // afirmar
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        Assertions.assertEquals("User " + idUserNotFound + " not found", response.getBody());

        Mockito.verify(userService, Mockito.times(1)).findUserById(idUserNotFound);
    }





}