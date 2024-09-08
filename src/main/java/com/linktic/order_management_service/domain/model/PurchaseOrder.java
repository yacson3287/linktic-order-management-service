package com.linktic.order_management_service.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseOrder {
    private Long id;
    private String customerName;
    private String email;
    private String mailingAddress;
    private PurchaseStatus status;
    private LocalDateTime createAt;
}
