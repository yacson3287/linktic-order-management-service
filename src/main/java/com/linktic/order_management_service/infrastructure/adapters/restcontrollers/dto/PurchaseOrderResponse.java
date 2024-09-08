package com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PurchaseOrderResponse {
    private Long id;
    private PurchaseStatus status;
    private LocalDateTime createAt;

    public static PurchaseOrderResponse convertFromDomain(PurchaseOrder purchaseOrder) {
        return new ModelMapper().map(purchaseOrder, PurchaseOrderResponse.class);
    }
}
