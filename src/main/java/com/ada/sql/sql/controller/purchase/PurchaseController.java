package com.ada.sql.sql.controller.purchase;


import com.ada.sql.sql.dto.purchase.PurchaseRequestDto;
import com.ada.sql.sql.dto.purchase.PurchaseUserDto;
import com.ada.sql.sql.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @PostMapping
    public ResponseEntity<PurchaseUserDto> doPurchase(@RequestBody PurchaseRequestDto dto){
        Optional<PurchaseUserDto> purchaseUserDto = service.doPurchase(dto.getIdUser(), dto.getTotalPurchase());
        return new ResponseEntity<>(purchaseUserDto.get(), HttpStatus.CREATED);
    }
}
