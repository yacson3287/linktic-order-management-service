package com.linktic.order_management_service.infrastructure.adapters.restcontrollers;

import com.linktic.order_management_service.application.usescases.AddItemToPurchaseOrderUseCaseImpl;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.ItemCreateRequest;
import com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto.ItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
@RequiredArgsConstructor
public class ItemRestController {

    private final AddItemToPurchaseOrderUseCaseImpl addItemToPurchaseOrderUseCase;


    @PostMapping
    public ItemResponse addItem(@RequestBody @Valid ItemCreateRequest request) {
        var item = addItemToPurchaseOrderUseCase.execute(request.convertToDomain());
        return ItemResponse.convertFromDomain(item);
    }

}
