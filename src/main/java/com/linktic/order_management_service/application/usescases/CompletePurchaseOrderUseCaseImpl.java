package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.exceptions.ExceptionDetail;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import com.linktic.order_management_service.domain.ports.in.CompletePurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompletePurchaseOrderUseCaseImpl implements CompletePurchaseOrderUseCase {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder execute(Long id) {
        var purchaseOrder = findById(id);
        validateStatus(purchaseOrder);

        return purchaseOrderRepository.save(purchaseOrder);
    }

    private void validateStatus(PurchaseOrder purchaseOrder) {
        if (PurchaseStatus.CANCELED == purchaseOrder.getStatus()) {
            var exception = new ExceptionDetail("It isn't possible complete this operation");
            exception.addDetail("status", "This Order was canceled");
            throw new BadRequestExceptionService(exception);
        }
        purchaseOrder.setStatus(PurchaseStatus.COMPLETE);
    }

    private PurchaseOrder findById(Long id) {

        var purchaseOrder = purchaseOrderRepository.findById(id);

        if (purchaseOrder == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }

        return purchaseOrder;
    }
}
