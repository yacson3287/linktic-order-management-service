package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.ports.in.FindPurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPurchaseOrderUseCaseImpl implements FindPurchaseOrderUseCase {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }
}
