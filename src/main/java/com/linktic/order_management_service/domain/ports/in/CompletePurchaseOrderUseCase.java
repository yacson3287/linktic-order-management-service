package com.linktic.order_management_service.domain.ports.in;

import com.linktic.order_management_service.domain.model.PurchaseOrder;

public interface CompletePurchaseOrderUseCase {
    PurchaseOrder execute(Long id);
}
