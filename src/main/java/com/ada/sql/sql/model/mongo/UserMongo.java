package com.ada.sql.sql.model.mongo;

import com.ada.sql.sql.model.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Document
public class UserMongo {

    @Id
    private String idUser;
    private String username;
    private String password;
    private String email;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private List<RoleEnum> roles;

    public UserMongo() {
        this.roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }

    public UserMongo(String username, String password, String email) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.email = email;
        this.dateCreation = LocalDateTime.now();
        this.dateUpdate = LocalDateTime.now();
        this.roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public void updateUser(UserMongo user){
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
        this.dateUpdate = LocalDateTime.now();
    }

}
