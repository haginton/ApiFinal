package com.ada.sql.sql.dto.user;

import com.ada.sql.sql.model.RoleEnum;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public class UserGenericDto {

    private String idUser;
    private String username;
    private String password;
    private String email;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private List<RoleEnum> roles;

    public UserGenericDto() {
    }

    public UserGenericDto(String username, String password, String email, LocalDateTime dateCreation, LocalDateTime dateUpdate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
    }

    public UserGenericDto(String idUser, String username, String password, String email, LocalDateTime dateCreation, LocalDateTime dateUpdate) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
    }

    public UserGenericDto(String idUser, String username, String password, String email, LocalDateTime dateCreation, LocalDateTime dateUpdate, List<RoleEnum> roles) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.roles = roles;
    }

    public UserGenericDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
}
