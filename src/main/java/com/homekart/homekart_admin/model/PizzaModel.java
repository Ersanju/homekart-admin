package com.homekart.homekart_admin.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaModel {

    private String id; // Unique pizza ID
    private String name; // Pizza name
    private String description; // Short description
    private boolean veg; // true = veg, false = non-veg

    // Sizes and prices
    private List<SizeOption> sizes; // e.g. Small, Medium, Large with price

    // Toppings
    private List<String> defaultToppings; // Comes with pizza
    private List<String> extraToppings; // Optional toppings

    // UI / Info
    private String imageUrl;
    private List<String> tags; // ["Spicy", "Cheesy", "Bestseller"]
    private boolean available;
    private String category;
    private Date createdAt;

    @Setter
    @Getter
    public static class SizeOption {
        private String sizeName; // e.g. "Small"
        private double price;

        public SizeOption() {}
        public SizeOption(String sizeName, double price) {
            this.sizeName = sizeName;
            this.price = price;
        }

    }

}
