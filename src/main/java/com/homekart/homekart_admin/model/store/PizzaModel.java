package com.homekart.homekart_admin.model.store;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PizzaModel {
    private List<PizzaVariant> variants;
}
