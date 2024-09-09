package com.linktic.order_management_service.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Item {
    private Long id;
    private Product product;
    private PurchaseOrder purchaseOrder;
    private int quantity;
    private LocalDateTime createAt;
}
