package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.exceptions.ExceptionDetail;
import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import com.linktic.order_management_service.domain.ports.in.AddItemToPurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.out.ItemRepository;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddItemToPurchaseOrderUseCaseImpl implements AddItemToPurchaseOrderUseCase {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item execute(Item item) {
        validatePurchaseOrder(item);
        item = validateItem(item);
        return itemRepository.save(item);
    }

    private Item validateItem(Item item) {
        var currentItem = itemRepository.findByProductAndPurchaseOrder(item.getProduct(), item.getPurchaseOrder());
        if (currentItem != null) {
            int quantity = currentItem.getQuantity() + item.getQuantity();
            currentItem.setQuantity(quantity);
            return currentItem;
        }
        return item;
    }

    private void validatePurchaseOrder(Item item) {
        var purchaseOrder = findPurchaseOrder(item.getPurchaseOrder().getId());
        validateStatusPurchaseOrder(purchaseOrder);
        item.setPurchaseOrder(purchaseOrder);
    }

    private void validateStatusPurchaseOrder(PurchaseOrder purchaseOrder) {
        if (PurchaseStatus.PENDING != purchaseOrder.getStatus()) {
            var exception = new ExceptionDetail("It isn't possible complete this operation");
            exception.addDetail("status", "It is not possible to aggregate items of this purchase order");
            throw new BadRequestExceptionService(exception);
        }
    }

    private PurchaseOrder findPurchaseOrder(Long id) {
        var purchaseOrder = purchaseOrderRepository.findById(id);

        if (purchaseOrder == null) {
            var exception = new ExceptionDetail("The resource not exist");
            exception.addDetail("id", "No exist a resource with this id");
            throw new BadRequestExceptionService(exception);
        }
        return purchaseOrder;
    }

}
