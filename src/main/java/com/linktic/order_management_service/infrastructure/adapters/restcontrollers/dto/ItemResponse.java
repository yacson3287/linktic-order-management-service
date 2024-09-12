package com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemResponse {

    private Long id;
    private Long purchaseOrderId;
    private Product product;
    private int quantity;

    public static ItemResponse convertFromDomain(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .purchaseOrderId(item.getId())
                .product(item.getProduct())
                .quantity(item.getQuantity())
                .build();
    }

}
