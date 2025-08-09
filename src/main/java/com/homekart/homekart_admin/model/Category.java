package com.homekart.homekart_admin.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String categoryId;
    private int priority;
    private boolean active;
    private Date createdAt;
}
