package com.homekart.homekart_admin.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private List<String> subCategoryIds;
    private Date createdAt;
}
