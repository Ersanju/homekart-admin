package com.homekart.homekart_admin.model;

import com.google.cloud.firestore.annotation.PropertyName;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private String id;
    private String name;
    private String categoryId;
    private List<String> subCategoryIds;
    private String productType;
    private List<String> imageUrls;
    private List<String> tags;
    private boolean isAvailable;
    private int stockQuantity;
    private int popularityScore;
    private List<String> productDescription;
    private List<String> careInstruction;
    private List<String> deliveryInformation;
    private ExtraAttributes extraAttributes;
    private List<Review> reviews;
    private Date createdAt;
    private Date updatedAt;
    private String createdBy;

    @PropertyName("isAvailable")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    @PropertyName("isAvailable")
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
