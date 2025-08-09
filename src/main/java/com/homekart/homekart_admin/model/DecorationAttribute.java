package com.homekart.homekart_admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecorationAttribute {
    private String decorationType;
    private String theme;
    private String colorScheme;
    private String color;
    private String size;
    private String material;
    private Integer durationInHours;
    private double price;
    private String sku;
}
