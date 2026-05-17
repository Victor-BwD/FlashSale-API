package FlashSale.infrastructure.api.dto;

import java.util.UUID;

public record PlaceOrderRequest(
        UUID customerId,
        UUID productId,
        Integer quantity
) {
}
