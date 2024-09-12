package com.linktic.order_management_service.domain.ports.in;

import com.linktic.order_management_service.domain.model.PurchaseOrder;

public interface CanceledPurchaseOrderUseCase {

    PurchaseOrder execute(Long id);
}
