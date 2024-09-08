package com.linktic.order_management_service.domain.ports.out;

import com.linktic.order_management_service.domain.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderRepository {

    PurchaseOrder save(PurchaseOrder purchaseOrder);

    PurchaseOrder findById(Long id);

    List<PurchaseOrder> findAll();

}
