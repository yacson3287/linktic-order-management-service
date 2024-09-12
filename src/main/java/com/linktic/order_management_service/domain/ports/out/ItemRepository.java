package com.linktic.order_management_service.domain.ports.out;

import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.domain.model.PurchaseOrder;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    List<Item> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Item findByProductAndPurchaseOrder(Product product, PurchaseOrder purchaseOrder);

    void delete(Item item);

}
