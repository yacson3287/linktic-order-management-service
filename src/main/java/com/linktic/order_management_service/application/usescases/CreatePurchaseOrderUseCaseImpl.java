package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import com.linktic.order_management_service.domain.ports.in.CreatePurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePurchaseOrderUseCaseImpl implements CreatePurchaseOrderUseCase {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder execute(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(PurchaseStatus.PENDING);
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
