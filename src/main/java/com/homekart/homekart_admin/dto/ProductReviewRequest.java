package com.homekart.homekart_admin.dto;

import com.homekart.homekart_admin.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewRequest {
    private String productId;
    private List<Review> reviews;
}
