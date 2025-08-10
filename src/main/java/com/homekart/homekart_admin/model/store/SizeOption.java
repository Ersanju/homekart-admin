package com.homekart.homekart_admin.model.store;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SizeOption {
    private String sizeName;
    private double price;
}
