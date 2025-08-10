package com.homekart.homekart_admin.model.store;

import com.google.cloud.Timestamp;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PizzaModel {
    private String id;
    private String name;
    private String description;
    private boolean veg;
    private List<SizeOption> sizes;
    private List<String> defaultToppings;
    private List<String> extraToppings;
    private String imageUrl;
    private List<String> tags;
    private boolean available;
    private String category;
    private Timestamp createdAt;
}
