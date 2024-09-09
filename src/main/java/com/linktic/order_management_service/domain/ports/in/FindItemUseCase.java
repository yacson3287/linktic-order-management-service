package com.linktic.order_management_service.domain.ports.in;

import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.PurchaseOrder;

import java.util.List;

public interface FindItemUseCase {

    List<Item> execute(PurchaseOrder purchaseOrder);
}
