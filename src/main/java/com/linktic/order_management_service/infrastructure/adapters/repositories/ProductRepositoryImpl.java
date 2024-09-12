package com.linktic.order_management_service.infrastructure.adapters.repositories;

import com.linktic.order_management_service.domain.exceptions.BadRequestExceptionService;
import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.domain.ports.out.ProductRepository;
import com.linktic.order_management_service.infrastructure.adapters.repositories.dto.UpdateQuantityProduct;
import com.linktic.order_management_service.infrastructure.feingclients.ProductCatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductCatalogService productCatalogService;

    @Override
    public Product findById(Long id) {
        return productCatalogService.findById(id);
    }

    @Override
    public List<Product> findByIds(List<Long> ids) {
        return productCatalogService.findByIds(ids);
    }

    @Override
    public Product subtractProduct(Long id, int quantity) {
        var updateQuantityProduct = UpdateQuantityProduct
                .builder()
                .id(id)
                .quantity(quantity)
                .build();
        try {
            return productCatalogService.subtractProduct(updateQuantityProduct);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BadRequestExceptionService("It is not possible completed this operation");
        }

    }

    @Override
    public List<Product> addProduct(List<Item> items) {
        var puq = items.stream()
                .map(UpdateQuantityProduct::convertFromItem)
                .toList();
        return productCatalogService.addProduct(puq);
    }


}
