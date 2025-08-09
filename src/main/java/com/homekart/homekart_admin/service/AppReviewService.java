package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.AppReview;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class AppReviewService {

    private static final String COLLECTION_NAME = "app_reviews";

    public String insertAppReviews(List<AppReview> reviews) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();

        for (AppReview review : reviews) {
            if (review.getId() == null || review.getId().isEmpty()) {
                review.setId(UUID.randomUUID().toString());
            }
            review.setCreatedAt(new Date());

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(review.getId());
            batch.set(docRef, review);
        }

        ApiFuture<List<WriteResult>> future = batch.commit();
        future.get(); // Wait for batch to complete

        return "Inserted " + reviews.size() + " app reviews successfully.";
    }

    public List<AppReview> getAllAppReviews() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<AppReview> reviews = new ArrayList<>();

        for (QueryDocumentSnapshot doc : documents) {
            AppReview review = doc.toObject(AppReview.class);
            reviews.add(review);
        }

        return reviews;
    }

}
