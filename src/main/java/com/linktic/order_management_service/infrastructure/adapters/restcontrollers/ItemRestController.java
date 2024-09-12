package com.linktic.order_management_service.infrastructure.adapters.restcontrollers;

import com.linktic.order_management_service.application.usescases.AddItemToPurchaseOrderUseCaseImpl;
import com.linktic.order_management_service.application.usescases.FindItemUseCaseImpl;
import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.ItemCreateRequest;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.ItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Add products to purchase orders")
public class ItemRestController {

    private final AddItemToPurchaseOrderUseCaseImpl addItemToPurchaseOrderUseCase;
    private final FindItemUseCaseImpl findItemUseCase;


    @Operation(summary = "Add an item to a purchase order")
    @PostMapping
    public ItemResponse addItem(@RequestBody @Valid ItemCreateRequest request) {
        var item = addItemToPurchaseOrderUseCase.execute(request.convertToDomain());
        return ItemResponse.convertFromDomain(item);
    }

    @Operation(summary = "Look items of a purchase order")
    @GetMapping("purchase-order/{id}")
    public List<ItemResponse> findByPurchaseOrder(@PathVariable Long id) {
        var items = findItemUseCase.execute(PurchaseOrder.builder().id(id).build());
        return items.stream()
                .map(ItemResponse::convertFromDomain)
                .toList();
    }

}
