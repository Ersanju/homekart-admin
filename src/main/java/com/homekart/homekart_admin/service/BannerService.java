package com.homekart.homekart_admin.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.homekart.homekart_admin.model.Banner;
import com.homekart.homekart_admin.utils.Util;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BannerService {

    private static final String COLLECTION_NAME = "banners";

    public String addMultipleBanners(List<Banner> banners) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        WriteBatch batch = db.batch();
        List<String> insertedIds = new ArrayList<>();

        for (Banner banner : banners) {
            if (banner.getId() == null || banner.getId().isEmpty()) {
                return "Each banner must have a non-empty ID";
            }

            // Convert Google Drive link
            banner.setImageUrl(Util.convertGoogleDriveLink(banner.getImageUrl()));

            DocumentReference docRef = db.collection(COLLECTION_NAME).document(banner.getId());
            batch.set(docRef, banner);
            insertedIds.add(banner.getId());
        }

        batch.commit().get();

        StringBuilder response = new StringBuilder();
        response.append("Inserted ").append(insertedIds.size()).append(" banners with IDs:\n");
        for (String id : insertedIds) {
            response.append(id).append("\n");
        }
        return response.toString();
    }

    public List<Banner> getAllBanners() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Banner> bannerList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Banner banner = document.toObject(Banner.class);
            bannerList.add(banner);
        }
        return bannerList;
    }
}
