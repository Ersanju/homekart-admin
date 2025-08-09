package com.homekart.homekart_admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftAttribute {
    private String type;
    private String size;
    private String color;
    private String material;
    private String personalizedMessage;
    private double price;
    private String sku;
}
