package com.ada.sql.sql.service.purchase;

import com.ada.sql.sql.dto.purchase.PurchaseUserDto;

import java.util.Optional;

public interface PurchaseService {

    Optional<PurchaseUserDto> doPurchase(String idUser, Double totalPurchase);
}
