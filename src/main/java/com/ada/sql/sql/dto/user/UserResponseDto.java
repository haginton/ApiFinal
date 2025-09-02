package com.ada.sql.sql.dto.user;

import java.time.LocalDateTime;

public class UserResponseDto {

    private String idUser;
    private String username;
    private String email;
    private LocalDateTime dateCreation;

    public UserResponseDto(String idUser, String username, String email, LocalDateTime dateCreation) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.dateCreation = dateCreation;
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

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "idUser='" + idUser + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
