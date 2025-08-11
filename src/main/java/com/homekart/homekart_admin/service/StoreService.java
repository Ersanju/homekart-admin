package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Review;
import com.homekart.homekart_admin.model.store.Store;
import com.homekart.homekart_admin.model.store.StoreItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class StoreService {

    private static final String COLLECTION_NAME = "stores";

    // Add multiple stores
    public String addStores(List<Store> stores) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        for (Store store : stores) {
            if (store.getId() == null || store.getId().isEmpty()) {
                store.setId(UUID.randomUUID().toString());
            }
            if (store.getCreatedAt() == null) {
                store.setCreatedAt(Timestamp.now());
            }

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(store.getId());
            batch.set(docRef, store);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        future.get(); // Wait for batch to complete

        return "Inserted " + stores.size() + " stores successfully.";
    }

    // Get all stores
    public List<Store> getAllStores() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Store> stores = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            Store store = doc.toObject(Store.class);
            stores.add(store);
        }

        return stores;
    }

    // Add store items with nested product types
    public String addItemsToStore(String storeId, List<StoreItem> items) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        CollectionReference itemsRef = db.collection(COLLECTION_NAME)
                .document(storeId)
                .collection("items");

        for (StoreItem item : items) {
            DocumentReference docRef;

            // Auto-generate ID if missing
            if (item.getId() == null || item.getId().isEmpty()) {
                docRef = itemsRef.document();
                item.setId(docRef.getId());
            } else {
                docRef = itemsRef.document(item.getId());
            }

            // Ensure timestamps
            if (item.getCreatedAt() == null) {
                item.setCreatedAt(Timestamp.now());
            }
            item.setUpdatedAt(Timestamp.now());

            // Ensure review timestamps
            if (item.getReviews() != null) {
                for (Review review : item.getReviews()) {
                    if (review.getCreatedAt() == null) {
                        review.setCreatedAt(Timestamp.now());
                    }
                }
            }

            batch.set(docRef, item);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        future.get(); // wait for completion

        return "Inserted " + items.size() + " items into store " + storeId;
    }

    // Fetch store items
    public List<StoreItem> getItemsByStore(String storeId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();

        CollectionReference itemsRef = db.collection(COLLECTION_NAME)
                .document(storeId)
                .collection("items");

        ApiFuture<QuerySnapshot> future = itemsRef.get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<StoreItem> items = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            StoreItem item = doc.toObject(StoreItem.class);
            items.add(item);
        }

        return items;
    }
}
