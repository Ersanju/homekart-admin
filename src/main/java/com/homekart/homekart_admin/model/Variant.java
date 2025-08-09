package com.homekart.homekart_admin.model;

import com.google.cloud.firestore.annotation.PropertyName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant {
    private String sku;
    private String weight;
    private int tier;
    private double price;
    private Double oldPrice;
    private int stockQuantity;
    private boolean isAvailable;

    @PropertyName("isAvailable")
    public Boolean getIsAvailable() {
        return isAvailable;
    }

    @PropertyName("isAvailable")
    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
