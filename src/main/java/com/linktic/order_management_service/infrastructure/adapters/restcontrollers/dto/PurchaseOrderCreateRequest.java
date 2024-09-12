package com.linktic.order_management_service.infrastructure.adapters.restcontrollers.dto;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;


@AllArgsConstructor
@Getter
public class PurchaseOrderCreateRequest {

    @NotBlank
    private String customerName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String mailingAddress;

    public PurchaseOrder convertToDomain() {
        return new ModelMapper().map(this, PurchaseOrder.class);
    }

}
