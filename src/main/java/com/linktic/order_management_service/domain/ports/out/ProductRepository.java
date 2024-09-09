package com.linktic.order_management_service.domain.ports.out;

import com.linktic.order_management_service.domain.model.Product;

public interface ProductRepository {

    Product findById(Long id);

    Product subtractProduct(Long id, int quantity);

    Product addProduct(Long id, int quantity);


}
