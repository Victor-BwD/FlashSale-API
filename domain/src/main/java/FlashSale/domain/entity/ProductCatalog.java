package FlashSale.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductCatalog {
    private UUID id;
    private String name;
    private BigDecimal price;

    public ProductCatalog(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private BigDecimal price;
        private Integer stockQuantity;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductCatalog build() {
            return new ProductCatalog(this);
        }
    }
}


