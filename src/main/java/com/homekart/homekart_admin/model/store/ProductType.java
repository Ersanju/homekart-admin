package com.homekart.homekart_admin.model.store;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {
    private PizzaModel pizzaModel;
    private SweetModel sweetModel;
}
