package com.ada.sql.sql.repository.postgresql.purchase;

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
@Profile("jpaImpl")
public class PurchaseRepositoryImpl implements PurchaseRepository {

    @Autowired
    private PurchaseRepositoryJpa purchaseRepositoryJpa;

    @Override
    public List<PurchaseGenericDto> getAllPurchases() {
        return purchaseRepositoryJpa.findAll()
                .stream()
                .map(purchase -> PurchaseMapper.convertPurchaseJpaToPurchaseGenericDto(purchase))
                .toList();
    }

    @Override
    public Optional<PurchaseGenericDto> findPurchaseById(String idPurchase) {
        return purchaseRepositoryJpa.findById(Long.parseLong(idPurchase))
                .map(purchase -> PurchaseMapper.convertPurchaseJpaToPurchaseGenericDto(purchase));
    }

    @Override
    public Optional<PurchaseGenericDto> save(PurchaseGenericDto purchase) {
        return Optional.of(PurchaseMapper.convertPurchaseJpaToPurchaseGenericDto(purchaseRepositoryJpa.save(PurchaseMapper.convertPurchaseGenericDtoToPurchaseJpa(purchase))));
    }

    @Override
    public Boolean deleteById(String idPurchase) {
        Optional<PurchaseGenericDto> purchaseFound = findPurchaseById(idPurchase);

        if (purchaseFound.isPresent()){
            purchaseRepositoryJpa.deleteById(Long.parseLong(idPurchase));
            return true;
        }

        return false;
    }
}
