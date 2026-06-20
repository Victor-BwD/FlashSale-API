CREATE TABLE inventory (
    id UUID PRIMARY KEY,
    product_id UUID NOT NULL UNIQUE,
    quantity INTEGER NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE orders (
   id UUID PRIMARY KEY,
   customer_id UUID NOT NULL,
   product_id UUID NOT NULL,
   quantity INTEGER NOT NULL,
   status VARCHAR(50) NOT NULL,
   created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_inventory_product_id ON inventory(product_id);