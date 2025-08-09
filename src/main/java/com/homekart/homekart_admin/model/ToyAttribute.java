package com.homekart.homekart_admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToyAttribute {
    private String ageGroup; // e.g., "3-5 years"
    private String material; // e.g., "Plastic", "Plush"
    private String brand;
    private double price;
    private String sku;
}
