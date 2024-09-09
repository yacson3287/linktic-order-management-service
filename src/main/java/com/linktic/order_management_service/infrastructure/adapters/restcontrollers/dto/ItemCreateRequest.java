package com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto;


import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ItemCreateRequest {

    @NotNull
    private Long productId;
    @NotNull
    private Long purchaseOrderId;
    @NotNull
    @Positive
    private int quantity;

    public Item convertToDomain() {
        return Item.builder()
                .purchaseOrder(PurchaseOrder.builder()
                        .id(getPurchaseOrderId())
                        .build()
                )
                .product(Product.builder()
                        .id(getProductId())
                        .build())
                .quantity(getQuantity())
                .build();
    }

}
