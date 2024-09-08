package com.linktic.order_management_service.infrastructure.adapters.repositories;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import com.linktic.order_management_service.infrastructure.db.jpa.PurchaseOrderJPARepository;
import com.linktic.order_management_service.infrastructure.db.model.PurchaseOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository {

    private final PurchaseOrderJPARepository purchaseOrderJPARepository;

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        var entity = purchaseOrderJPARepository.save(PurchaseOrderEntity.convertFromDomain(purchaseOrder));
        return entity.convertToDomain();
    }

    @Override
    public PurchaseOrder findById(Long id) {
        var entity = purchaseOrderJPARepository.findById(id);
        return entity.map(PurchaseOrderEntity::convertToDomain).orElse(null);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        var entities = purchaseOrderJPARepository.findAll();
        return entities.stream()
                .map(PurchaseOrderEntity::convertToDomain)
                .toList();
    }

}
