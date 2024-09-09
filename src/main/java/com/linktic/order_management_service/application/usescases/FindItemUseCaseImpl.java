package com.linktic.order_management_service.application.usescases;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.exceptions.ExceptionDetail;
import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.ports.in.FindItemUseCase;
import com.linktic.order_management_service.domain.ports.out.ItemRepository;
import com.linktic.order_management_service.domain.ports.out.ProductRepository;
import com.linktic.order_management_service.domain.ports.out.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindItemUseCaseImpl implements FindItemUseCase {

    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public List<Item> execute(PurchaseOrder purchaseOrder) {
        purchaseOrder = findPurchaseOrder(purchaseOrder.getId());
        var items = itemRepository.findByPurchaseOrder(purchaseOrder);
        addProductDetail(items);
        return items;
    }

    private void addProductDetail(List<Item> items) {
        var products = findProducts(items);
        items.forEach(item -> item.setProduct(filterProduct(item.getProduct().getId(), products)));
    }

    private Product filterProduct(Long productId, List<Product> products) {
        return products.stream()
                .filter(product -> Objects.equals(product.getId(), productId))
                .findFirst()
                .orElse(null);
    }

    private List<Product> findProducts(List<Item> items) {
        var productsIds = items.stream().map(item -> item.getProduct().getId()).toList();
        return productRepository.findByIds(productsIds);
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
