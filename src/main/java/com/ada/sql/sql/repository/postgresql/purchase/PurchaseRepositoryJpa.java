package com.ada.sql.sql.repository.postgresql.purchase;

import com.ada.sql.sql.model.sql.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepositoryJpa extends JpaRepository<Purchase, Long> {
}
