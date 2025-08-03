package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Product;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductService {

    private final Firestore firestore = FirestoreClient.getFirestore();

    public String addProduct(Product product) throws Exception {
        String productId = UUID.randomUUID().toString();
        product.setId(productId);
        product.setCreatedAt(new Date());

        ApiFuture<WriteResult> result = firestore
                .collection("products")
                .document(productId)
                .set(product);

        return result.get().getUpdateTime().toString();
    }
}
