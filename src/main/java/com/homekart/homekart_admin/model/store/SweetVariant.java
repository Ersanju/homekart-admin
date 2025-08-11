package com.homekart.homekart_admin.model.store;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SweetVariant {
    private String sku;
    private String sizeName;
    private double price;
    private boolean available;
    private Integer stockQuantity;
}
