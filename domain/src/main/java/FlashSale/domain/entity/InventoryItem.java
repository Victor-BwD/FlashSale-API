package FlashSale.domain.entity;

import java.util.UUID;

public class InventoryItem {
    private final UUID id;
    private final UUID productId;
    private Integer quantity;

    public InventoryItem(Builder builder) {
        this.id = builder.id;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity to decrease must be positive");
        }

        if(this.quantity < quantity) {
            throw new IllegalArgumentException("Insufficient stock to decrease");
        }

        this.quantity -= quantity;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static class Builder {
        private UUID id;
        private UUID productId;
        private Integer quantity;

        public Builder id(UUID id) {
            this.id = id;
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

        public InventoryItem build() {
            return new InventoryItem(this);
        }
    }
}
