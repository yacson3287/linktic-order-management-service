package com.linktic.order_management_service.infrastructure.adapters.repositories;

import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.ports.out.ItemRepository;
import com.linktic.order_management_service.infrastructure.db.jpa.ItemJPARepository;
import com.linktic.order_management_service.infrastructure.db.model.ItemEntity;
import com.linktic.order_management_service.infrastructure.db.model.PurchaseOrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJPARepository itemJPARepository;

    @Override
    public Item save(Item item) {
        var entity = itemJPARepository.save(ItemEntity.convertFromDomain(item));
        return entity.convertToDomain();
    }

    @Override
    public List<Item> findByPurchaseOrder(PurchaseOrder purchaseOrder) {
        return List.of();
    }

    @Override
    public Item findByProductAndPurchaseOrder(Product product, PurchaseOrder purchaseOrder) {
        var entity = itemJPARepository.findByProductIdAndPurchaseOrder(product.getId(), PurchaseOrderEntity.convertFromDomain(purchaseOrder));
        return entity.map(ItemEntity::convertToDomain).orElse(null);
    }

    @Override
    public void delete(Item item) {

    }
}
