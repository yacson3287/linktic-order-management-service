package com.linktic.order_management_service.domain.ports.in;

import com.linktic.order_management_service.domain.model.PurchaseOrder;

import java.util.List;

public interface FindPurchaseOrderUseCase {

    List<PurchaseOrder> findAll();
}
