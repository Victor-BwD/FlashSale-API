package FlashSale.infrastructure.api.dto;

import FlashSale.domain.model.OrderStatus;

import java.util.UUID;

public record PlaceOrderResponse(
        UUID orderId,
        OrderStatus status,
        String message
) {
}
