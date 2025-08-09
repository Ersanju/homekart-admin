package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Store;
import com.homekart.homekart_admin.model.StoreMenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class StoreService {

    private static final String COLLECTION_NAME = "stores";

    public String insertStores(List<Store> stores) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        for (Store store : stores) {
            if (store.getId() == null || store.getId().isEmpty()) {
                store.setId(UUID.randomUUID().toString());
            }
            store.setCreatedAt(new Date());

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(store.getId());
            batch.set(docRef, store);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        future.get(); // Wait for batch to complete

        return "Inserted " + stores.size() + " stores successfully.";
    }

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

    public String insertStoreMenu(String storeId, List<StoreMenuItem> menuItems) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        CollectionReference menuCollection = db.collection("stores").document(storeId).collection("menu");

        for (StoreMenuItem item : menuItems) {
            if (item.getId() == null || item.getId().isEmpty()) {
                item.setId(UUID.randomUUID().toString());
            }
            DocumentReference docRef = menuCollection.document(item.getId());
            batch.set(docRef, item);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        future.get();

        return "Inserted " + menuItems.size() + " menu items for store " + storeId + " successfully.";
    }

    public List<StoreMenuItem> getStoreMenu(String storeId) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("stores")
                .document(storeId)
                .collection("menu")
                .get();

        List<StoreMenuItem> menuList = new ArrayList<>();
        for (QueryDocumentSnapshot doc : future.get().getDocuments()) {
            menuList.add(doc.toObject(StoreMenuItem.class));
        }

        return menuList;
    }
}
