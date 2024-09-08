package com.linktic.order_management_service.infrastructure.db.model;

import com.linktic.order_management_service.domain.model.PurchaseOrder;
import com.linktic.order_management_service.domain.model.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "purchase_orders")
public class PurchaseOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String email;
    private String mailingAddress;
    private PurchaseStatus status;
    private LocalDateTime createAt;

    @PrePersist
    private void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    public static PurchaseOrderEntity convertFromDomain(PurchaseOrder purchaseOrder) {
        return new ModelMapper().map(purchaseOrder, PurchaseOrderEntity.class);
    }

    public PurchaseOrder convertToDomain() {
        return new ModelMapper().map(this, PurchaseOrder.class);
    }
}
