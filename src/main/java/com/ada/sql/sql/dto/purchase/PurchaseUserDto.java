package com.ada.sql.sql.dto.purchase;

import java.time.LocalDate;

public class PurchaseUserDto {

    private String idPurchase;
    private LocalDate datePurchase;
    private Double totalPurchase;
    private String idUser;
    private String username;

    public PurchaseUserDto(String idPurchase, LocalDate datePurchase, Double totalPurchase, String idUser, String username) {
        this.idPurchase = idPurchase;
        this.datePurchase = datePurchase;
        this.totalPurchase = totalPurchase;
        this.idUser = idUser;
        this.username = username;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public LocalDate getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDate datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(Double totalPurchase) {
        this.totalPurchase = totalPurchase;
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
}
