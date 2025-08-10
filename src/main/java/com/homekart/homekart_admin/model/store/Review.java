package com.homekart.homekart_admin.model.store;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private String userId;
    private String comment;
    private int rating;
    private String reviewerName;
    private com.google.cloud.Timestamp createdAt;
}
