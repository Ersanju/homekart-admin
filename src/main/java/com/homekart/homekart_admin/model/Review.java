package com.homekart.homekart_admin.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private String userId;
    private String userName;
    private List<String> imageUrls;
    private double rating;
    private String comment;
    private String occasion;
    private String place;
    private Date createdAt;
}
