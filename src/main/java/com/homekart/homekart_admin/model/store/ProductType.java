package com.homekart.homekart_admin.model.store;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductType {
    private PizzaModel pizzaModel;
    private SweetModel sweetModel;
}
