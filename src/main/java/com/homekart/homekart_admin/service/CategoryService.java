package com.homekart.homekart_admin.service;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Category;
import com.homekart.homekart_admin.utils.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CategoryService {

    private static final String COLLECTION_NAME = "categories";

    public String addMultipleCategories(List<Category> categories) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        List<String> insertedIds = new ArrayList<>();

        for (Category category : categories) {
            if (category.getId() == null || category.getId().isEmpty()) {
                return "Each category must have a non-empty ID";
            }
            category.setCreatedAt(new Date());

            // Convert Google Drive link for imageUrl
            category.setImageUrl(Util.convertGoogleDriveLink(category.getImageUrl()));

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(category.getId());
            batch.set(docRef, category);
            insertedIds.add(category.getId());
        }
        batch.commit().get();

        StringBuilder response = new StringBuilder();
        response.append("Inserted ").append(insertedIds.size()).append(" categories with IDs:\n");
        for (String id : insertedIds) {
            response.append(id).append("\n");
        }
        return response.toString();
    }
}
