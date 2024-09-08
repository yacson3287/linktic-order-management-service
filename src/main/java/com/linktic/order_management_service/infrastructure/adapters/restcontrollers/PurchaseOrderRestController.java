package com.linktic.order_management_service.infrastructure.adapters.restcontrollers;

import com.linktic.order_management_service.domain.ports.in.CompletePurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.in.CreatePurchaseOrderUseCase;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderCreateRequest;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderRestController {

    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;
    private final CompletePurchaseOrderUseCase completePurchaseOrderUseCase;

    @PostMapping
    public PurchaseOrderResponse create(@RequestBody PurchaseOrderCreateRequest request) {
        var purchaseOrder = createPurchaseOrderUseCase.execute(request.convertToDomain());
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

    @PutMapping("/complete/{id}")
    public PurchaseOrderResponse completedOrder(@PathVariable Long id) {
        var purchaseOrder = completePurchaseOrderUseCase.execute(id);
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

}
