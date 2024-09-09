package com.linktic.order_management_service.infrastructure.db.jpa;

import com.linktic.order_management_service.infrastructure.db.model.ItemEntity;
import com.linktic.order_management_service.infrastructure.db.model.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemJPARepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByProductIdAndPurchaseOrder(Long productId, PurchaseOrderEntity purchaseOrder);

    List<ItemEntity> findByPurchaseOrder(PurchaseOrderEntity purchaseOrder);

}
