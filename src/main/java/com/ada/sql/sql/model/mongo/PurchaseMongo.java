package com.ada.sql.sql.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class PurchaseMongo {

    @Id
    private String idPurchase;
    private String idUser;
    private LocalDate datePurchase;
    private Double totalPurchase;

    public PurchaseMongo() {
    }

    public PurchaseMongo(String idUser, Double totalPurchase) {
        this.idUser = idUser;
        this.datePurchase = LocalDate.now();
        this.totalPurchase = totalPurchase;
    }

    public String getIdPurchase() {
        return idPurchase;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "idPurchase=" + idPurchase +
                ", idUser=" + idUser +
                ", datePurchase=" + datePurchase +
                ", totalPurchase=" + totalPurchase +
                '}';
    }
}
