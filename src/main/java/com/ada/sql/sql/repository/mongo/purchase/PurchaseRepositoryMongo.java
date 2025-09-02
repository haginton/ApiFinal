package com.ada.sql.sql.repository.mongo.purchase;

import com.ada.sql.sql.model.mongo.PurchaseMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchaseRepositoryMongo extends MongoRepository<PurchaseMongo, String> {
}
