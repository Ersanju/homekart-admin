package com.homekart.homekart_admin.model.store;

import com.google.cloud.Timestamp;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    private String id;
    private String name;
    private String storeType;
    private String imageUrl;
    private String description;
    private String address;
    private String phoneNumber;
    private double latitude;
    private double longitude;
    private List<String> categories; // optional
    private Timestamp createdAt;
}
