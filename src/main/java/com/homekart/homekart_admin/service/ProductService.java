package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Product;
import com.homekart.homekart_admin.model.Review;
import com.homekart.homekart_admin.utils.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private static final String COLLECTION_NAME = "products";

    public String addMultipleProducts(List<Product> products) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        List<String> insertedIds = new ArrayList<>();

        for (Product product : products) {
            if (product.getId() == null || product.getId().isEmpty()) {
                return "Each product must have a non-empty ID";
            }
            product.setCreatedAt(new Date());

            // Use Util method to convert image URLs
            List<String> convertedUrls = new ArrayList<>();
            for (String imageUrl : product.getImageUrls()) {
                convertedUrls.add(Util.convertGoogleDriveLink(imageUrl));
            }
            product.setImageUrls(convertedUrls);

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(product.getId());
            batch.set(docRef, product);
            insertedIds.add(product.getId());
        }
        batch.commit().get();

        StringBuilder response = new StringBuilder();
        response.append("Inserted ").append(insertedIds.size()).append(" products with IDs:\n");
        for (String id : insertedIds) {
            response.append(id).append("\n");
        }

        return response.toString();
    }

    public Product getProductById(String productId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(productId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (snapshot.exists()) {
            return snapshot.toObject(Product.class);
        } else {
            return null;
        }
    }

    public List<Product> getProductsByCategory(String categoryId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME)
                .whereEqualTo("categoryId", categoryId)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Product> products = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            products.add(doc.toObject(Product.class));
        }
        return products;
    }




    public String addReviewsToProduct(String productId, List<Review> newReviews) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(productId);
        DocumentSnapshot snapshot = docRef.get().get();

        if (!snapshot.exists()) {
            return "Product with ID " + productId + " does not exist.";
        }

        Product product = snapshot.toObject(Product.class);
        assert product != null;
        List<Review> existingReviews = product.getReviews();

        if (existingReviews == null) {
            existingReviews = new ArrayList<>();
        }

        existingReviews.addAll(newReviews);
        product.setReviews(existingReviews);

        docRef.update("reviews", existingReviews);
        return "Added " + newReviews.size() + " reviews to product ID: " + productId;
    }


}
