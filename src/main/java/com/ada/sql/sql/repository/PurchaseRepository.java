package com.ada.sql.sql.repository;

import com.ada.sql.sql.dto.purchase.PurchaseGenericDto;
import com.ada.sql.sql.model.sql.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {

    List<PurchaseGenericDto> getAllPurchases();
    Optional<PurchaseGenericDto> findPurchaseById(String idPurchase);
    Optional<PurchaseGenericDto> save(PurchaseGenericDto purchase);
    Boolean deleteById(String idPurchase);
}
