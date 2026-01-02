package FlashSale.usecase;

import FlashSale.domain.entity.InventoryItem;
import FlashSale.domain.entity.Order;
import FlashSale.domain.gateway.InventoryGateway;
import FlashSale.domain.gateway.OrderGateway;
import FlashSale.domain.model.OrderStatus;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class PlaceOrderUseCase {
    private final InventoryGateway inventoryGateway;
    private final OrderGateway orderGateway;

    public Order execute(UUID customerId, UUID productId, Integer quantity) {
        InventoryItem item = inventoryGateway.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found for product ID: " + productId));

        if(item.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for product ID: " + productId);
        }

        item.decreaseStock(quantity);

        Order order = new Order.Builder()
                .id(UUID.randomUUID())
                .customerId(customerId)
                .productId(productId)
                .quantity(quantity)
                .status(OrderStatus.PENDING)
                .createdAt(java.time.LocalDateTime.now())
                .build();

        inventoryGateway.save(item);
        orderGateway.save(order);

        return order;
    }
}
