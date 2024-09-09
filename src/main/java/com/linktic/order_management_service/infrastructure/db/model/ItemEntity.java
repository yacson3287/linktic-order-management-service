package com.linktic.order_management_service.infrastructure.db.model;


import com.linktic.order_management_service.domain.model.Item;
import com.linktic.order_management_service.domain.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrderEntity purchaseOrder;

    private LocalDateTime createAt;

    @PrePersist
    private void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    public Item convertToDomain() {
        return Item.builder()
                .id(getId())
                .product(Product.builder()
                        .id(getProductId())
                        .build())
                .quantity(getQuantity())
                .createAt(getCreateAt())
                .purchaseOrder(getPurchaseOrder().convertToDomain())
                .build();
    }

    public static ItemEntity convertFromDomain(Item item) {
        return ItemEntity.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .purchaseOrder(PurchaseOrderEntity.convertFromDomain(item.getPurchaseOrder()))
                .createAt(item.getCreateAt())
                .build();
    }

}
