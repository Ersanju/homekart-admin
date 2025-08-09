package com.homekart.homekart_admin.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenuItem {
    private String id;          // Auto-generated UUID if not provided
    private String name;        // e.g., "Veg Burger"
    private String description; // Optional: "Made with fresh veggies"
    private String imageUrl;    // Image link for the menu item
    private double price;       // Price in INR
    private boolean available;  // Is it currently available
}
