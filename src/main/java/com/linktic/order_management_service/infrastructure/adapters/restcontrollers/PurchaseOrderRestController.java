package com.linktic.order_management_service.infrastructure.adapters.restcontrollers;

import com.linktic.order_management_service.domain.ports.in.CanceledPurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.in.CompletePurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.in.CreatePurchaseOrderUseCase;
import com.linktic.order_management_service.domain.ports.in.FindPurchaseOrderUseCase;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderCreateRequest;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.PurchaseOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("purchase-order")
@RequiredArgsConstructor
@Tag(name = "Purchase Order", description = "Manage purchase orders")
public class PurchaseOrderRestController {

    private final CreatePurchaseOrderUseCase createPurchaseOrderUseCase;
    private final CompletePurchaseOrderUseCase completePurchaseOrderUseCase;
    private final CanceledPurchaseOrderUseCase canceledPurchaseOrderUseCase;
    private final FindPurchaseOrderUseCase findPurchaseOrderUseCase;


    @Operation(summary = "Create new Purchase order")
    @PostMapping
    public PurchaseOrderResponse create(@RequestBody PurchaseOrderCreateRequest request) {
        var purchaseOrder = createPurchaseOrderUseCase.execute(request.convertToDomain());
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

    @Operation(summary = "Complete Purchase order")
    @PutMapping("/complete/{id}")
    public PurchaseOrderResponse completedOrder(@PathVariable Long id) {
        var purchaseOrder = completePurchaseOrderUseCase.execute(id);
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

    @Operation(summary = "Cancel Purchase order")
    @PutMapping("/cancel/{id}")
    public PurchaseOrderResponse cancelOrder(@PathVariable Long id) {
        var purchaseOrder = canceledPurchaseOrderUseCase.execute(id);
        return PurchaseOrderResponse.convertFromDomain(purchaseOrder);
    }

    @Operation(summary = "Find all purchase order")
    @GetMapping
    public List<PurchaseOrderResponse> findAll() {
        var purchaseOrders = findPurchaseOrderUseCase.findAll();
        return purchaseOrders.stream()
                .map(PurchaseOrderResponse::convertFromDomain)
                .toList();
    }

}
