package FlashSale.domain.entity;

import FlashSale.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final UUID customerId;
    private final UUID productId;
    private final Integer quantity;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    public Order(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static class Builder {
        private UUID id;
        private UUID customerId;
        private UUID productId;
        private Integer quantity;
        private OrderStatus status;
        private LocalDateTime createdAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder customerId(UUID customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder productId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
