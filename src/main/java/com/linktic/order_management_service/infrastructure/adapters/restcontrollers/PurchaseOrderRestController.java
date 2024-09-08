package com.linktic.order_management_service.infrastructure.adapters.restcontrollers;

import com.linktic.order_management_service.domain.ports.in.CreatePurchaseOrderUseCase;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderCreateRequest;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderRestController {

    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;

    @PostMapping
    public PurchaseOrderResponse create(@RequestBody PurchaseOrderCreateRequest request) {
        var purchaseOrder = createPurchaseOrderUseCase.execute(request.convertToDomain());
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

}
