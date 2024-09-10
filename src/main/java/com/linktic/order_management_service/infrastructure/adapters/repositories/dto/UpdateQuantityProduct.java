package com.linktic.order_management_service.infrastructure.adapters.repositories.dto;

import com.linktic.order_management_service.domain.model.Item;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateQuantityProduct {
    private Long id;
    private Integer quantity;


    public static UpdateQuantityProduct convertFromItem(Item item) {
        return UpdateQuantityProduct.builder()
                .id(item.getProduct().getId())
                .quantity(item.getQuantity())
                .build();
    }
}
