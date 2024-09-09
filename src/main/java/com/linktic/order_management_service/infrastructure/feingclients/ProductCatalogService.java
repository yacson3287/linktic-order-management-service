package com.linktic.order_management_service.infrastructure.feingclients;

import com.linktic.order_management_service.domain.model.Product;
import com.linktic.order_management_service.infrastructure.adapters.repositories.dto.UpdateQuantityProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-catalog-service", url = "http://localhost:8001")
public interface ProductCatalogService {

    @GetMapping("/product/{id}")
    Product findById(@PathVariable Long id);

    @PutMapping("/product/subtract-quantity")
    Product subtractProduct(@RequestBody UpdateQuantityProduct updateQuantityProduct);


}
