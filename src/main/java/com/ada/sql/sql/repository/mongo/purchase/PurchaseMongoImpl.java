package com.ada.sql.sql.repository.mongo.purchase;

import com.ada.sql.sql.dto.purchase.PurchaseGenericDto;
import com.ada.sql.sql.model.sql.Purchase;
import com.ada.sql.sql.repository.PurchaseRepository;
import com.ada.sql.sql.util.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("mongoImpl")
public class PurchaseMongoImpl implements PurchaseRepository{

    @Autowired
    private PurchaseRepositoryMongo purchaseRepositoryMongo;

    @Override
    public List<PurchaseGenericDto> getAllPurchases() {
        return purchaseRepositoryMongo.findAll()
                .stream()
                .map(purchaseMongo -> PurchaseMapper.convertPurchaseMongoToPurchaseGenericDto(purchaseMongo))
                .toList();
    }

    @Override
    public Optional<PurchaseGenericDto> findPurchaseById(String idPurchase) {
        return purchaseRepositoryMongo.findById(idPurchase)
                .map(purchaseMongo -> PurchaseMapper.convertPurchaseMongoToPurchaseGenericDto(purchaseMongo));
    }

    @Override
    public Optional<PurchaseGenericDto> save(PurchaseGenericDto purchase) {
        return Optional.of(PurchaseMapper.convertPurchaseMongoToPurchaseGenericDto(purchaseRepositoryMongo.save(PurchaseMapper.convertPurchaseGenericDtoToPurchaseMongo(purchase))));
    }

    @Override
    public Boolean deleteById(String idPurchase) {
        Optional<PurchaseGenericDto> purchaseFound = findPurchaseById(idPurchase);
        if (purchaseFound.isPresent()){
            purchaseRepositoryMongo.deleteById(idPurchase);
            return true;
        }
        return false;
    }
}