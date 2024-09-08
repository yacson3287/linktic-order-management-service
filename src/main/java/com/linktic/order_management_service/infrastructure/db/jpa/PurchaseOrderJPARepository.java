package com.linktic.order_management_service.infrastructure.db.jpa;

import com.linktic.order_management_service.infrastructure.db.model.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderJPARepository extends JpaRepository<PurchaseOrderEntity, Long> {

}
