package com.linktic.order_management_service.domain.ports.in;

import com.linktic.order_management_service.domain.model.Item;

public interface AddItemToPurchaseOrderUseCase {

    Item execute(Item item);

}
