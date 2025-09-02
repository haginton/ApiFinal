package com.ada.sql.sql.dto.purchase;

public class PurchaseRequestDto {

    private String idUser;
    private Double totalPurchase;

    public PurchaseRequestDto(String idUser, Double totalPurchase) {
        this.idUser = idUser;
        this.totalPurchase = totalPurchase;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Double getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(Double totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
}
