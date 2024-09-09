package com.linktic.order_management_service.domain.ports.out;

import com.linktic.order_management_service.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product findById(Long id);

    List<Product> findByIds(List<Long> ids);

    Product subtractProduct(Long id, int quantity);

    Product addProduct(Long id, int quantity);


}
