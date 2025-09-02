package com.ada.sql.sql.dto.purchase;

import java.time.LocalDate;

public class PurchaseGenericDto {

    private String idPurchase;
    private String idUser;
    private LocalDate datePurchase;
    private Double totalPurchase;

    public PurchaseGenericDto() {
    }

    public PurchaseGenericDto(String idUser, LocalDate datePurchase, Double totalPurchase) {
        this.idUser = idUser;
        this.datePurchase = datePurchase;
        this.totalPurchase = totalPurchase;
    }

    public PurchaseGenericDto(String idPurchase, String idUser, LocalDate datePurchase, Double totalPurchase) {
        this.idPurchase = idPurchase;
        this.idUser = idUser;
        this.datePurchase = datePurchase;
        this.totalPurchase = totalPurchase;
    }

    public PurchaseGenericDto(String idUser, Double totalPurchase) {
        this.idUser = idUser;
        this.totalPurchase = totalPurchase;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
}
