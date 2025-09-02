package com.ada.sql.sql.model.sql;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_purchase")
    private Long idPurchase;
    @Column(name = "id_user")
    private Long idUser;
    @Column(name = "date_purchase")
    private LocalDate datePurchase;
    @Column(name = "total_purchase")
    private Double totalPurchase;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    public Purchase() {
    }

    public Purchase(Long idUser, Double totalPurchase) {
        this.idUser = idUser;
        this.datePurchase = LocalDate.now();
        this.totalPurchase = totalPurchase;
    }

    public Long getIdPurchase() {
        return idPurchase;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
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
