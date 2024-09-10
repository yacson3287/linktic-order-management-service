package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.exceptions.ExceptionDetail;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import com.linktic.order_management_service.domain.ports.in.CanceledPurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.out.ItemRepository;
import com.linktic.order_management_service.domain.ports.out.ProductRepository;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CanceledPurchaseOrderUseCaseImpl implements CanceledPurchaseOrderUseCase {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    @Override
    public PurchaseOrder execute(Long id) {
        var purchaseOrder = findById(id);
        validateStatus(purchaseOrder);
        var items = itemRepository.findByPurchaseOrder(purchaseOrder);
        productRepository.addProduct(items);
        return purchaseOrderRepository.save(purchaseOrder);
    }

    private void validateStatus(PurchaseOrder purchaseOrder) {
        if (PurchaseStatus.PENDING != purchaseOrder.getStatus()) {
            var exception = new ExceptionDetail("It isn't possible completed this operation");
            exception.addDetail("status", "This order is not active");
            throw new BadRequestExceptionService(exception);
        }
        purchaseOrder.setStatus(PurchaseStatus.CANCELED);
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
