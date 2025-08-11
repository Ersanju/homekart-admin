package com.homekart.homekart_admin.model.store;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.PropertyName;
import com.homekart.homekart_admin.model.Review;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreItem {
    private String id;
    private String name;
    private String storeType; // "pizza", "sweet"
    @PropertyName("isVeg")
    private boolean isVeg;
    private List<String> imageUrls;
    private List<String> tags;
    @PropertyName("isAvailable")
    private boolean isAvailable;
    private int stockQuantity;
    private Integer popularityScore; // Nullable
    private String description;
    private ProductType productType; // Nested type
    private List<Review> reviews;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;

    @PropertyName("isVeg")
    public boolean getIsVeg() {
        return isVeg;
    }

    @PropertyName("isVeg")
    public void setIsVeg(boolean isVeg) {
        this.isVeg = isVeg;
    }

    @PropertyName("isAvailable")
    public boolean getIsAvailable() {
        return isAvailable;
    }

    @PropertyName("isAvailable")
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
