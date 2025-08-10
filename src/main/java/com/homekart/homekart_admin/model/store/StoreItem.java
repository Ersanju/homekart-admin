package com.homekart.homekart_admin.model.store;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreItem {

    @DocumentId
    private String id;
    private String name;
    private String storeType; // pizza, sweet, etc.
    private boolean isVeg;
    private List<String> imageUrls;
    private List<String> tags;
    private boolean isAvailable;
    private int stockQuantity;
    private Integer popularityScore;
    private String description;
    private ProductType productType;
    private List<Review> reviews;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
}
