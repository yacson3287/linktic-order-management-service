package com.linktic.order_management_service.infrastructure.adapters.repositories.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateQuantityProduct {
    private Long productId;
    private Integer quantity;
}
