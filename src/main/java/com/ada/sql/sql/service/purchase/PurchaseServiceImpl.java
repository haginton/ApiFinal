package com.ada.sql.sql.service.purchase;

import com.ada.sql.sql.dto.purchase.PurchaseGenericDto;
import com.ada.sql.sql.dto.purchase.PurchaseUserDto;
import com.ada.sql.sql.dto.user.UserGenericDto;
import com.ada.sql.sql.model.sql.Purchase;
import com.ada.sql.sql.model.sql.User;
import com.ada.sql.sql.repository.PurchaseRepository;
import com.ada.sql.sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    @Autowired
    //@Qualifier("userRepositoryImpl")
    //@Qualifier("userMongoRepositoryImpl")
    private UserRepository userRepository;

    @Autowired
    //@Qualifier("purchaseRepositoryImpl")
    //@Qualifier("purchaseMongoImpl")
    private PurchaseRepository purchaseRepository;

    @Override
    public Optional<PurchaseUserDto> doPurchase(String idUser, Double totalPurchase) {
        Optional<UserGenericDto> userFound = userRepository.findUserById(idUser);
        if (userFound.isPresent()){
            Optional<PurchaseGenericDto> purchase = purchaseRepository.save(new PurchaseGenericDto(idUser, totalPurchase));
            if (purchase.isPresent()){
                PurchaseGenericDto purchaseRealized = purchase.get();
                return Optional.of(new PurchaseUserDto(
                            purchaseRealized.getIdPurchase(),
                            purchaseRealized.getDatePurchase(),
                            purchaseRealized.getTotalPurchase(),
                            purchaseRealized.getIdUser(),
                            userFound.get().getUsername()
                        ));
            }
        }
        return Optional.empty();
    }
}
