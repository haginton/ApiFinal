package com.ada.sql.sql.util;

import com.ada.sql.sql.dto.purchase.PurchaseGenericDto;
import com.ada.sql.sql.model.mongo.PurchaseMongo;
import com.ada.sql.sql.model.sql.Purchase;

public class PurchaseMapper {

    public static PurchaseGenericDto convertPurchaseJpaToPurchaseGenericDto(Purchase purchase){
        return new PurchaseGenericDto(
                String.valueOf(purchase.getIdPurchase()),
                String.valueOf(purchase.getIdUser()),
                purchase.getDatePurchase(),
                purchase.getTotalPurchase()

        );
    }

    public static Purchase convertPurchaseGenericDtoToPurchaseJpa(PurchaseGenericDto purchase){
        return new Purchase(
                Long.parseLong(purchase.getIdUser()),
                purchase.getTotalPurchase()
        );
    }

    public static PurchaseGenericDto convertPurchaseMongoToPurchaseGenericDto(PurchaseMongo purchase){
        return new PurchaseGenericDto(
                purchase.getIdPurchase(),
                purchase.getIdUser(),
                purchase.getDatePurchase(),
                purchase.getTotalPurchase()
        );
    }

    public static PurchaseMongo convertPurchaseGenericDtoToPurchaseMongo(PurchaseGenericDto purchase){
        return new PurchaseMongo(
                purchase.getIdUser(),
                purchase.getTotalPurchase()
        );
    }
}
