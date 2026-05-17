package FlashSale.infrastructure.api.controller;

import FlashSale.controller.OrderController;
import FlashSale.infrastructure.api.dto.PlaceOrderRequest;
import FlashSale.infrastructure.api.dto.PlaceOrderResponse;
import org.springframework.stereotype.Component;

@Component
public class OrderApiMapper {
    public OrderController.OrderInput toInput(PlaceOrderRequest request) {
        return new OrderController.OrderInput(
                request.customerId(),
                request.productId(),
                request.quantity()
        );
    }

    public PlaceOrderResponse toResponse(OrderController.OrderOutput output) {
        return new PlaceOrderResponse(
                output.id(),
                output.status(),
                output.message()
        );
    }
}
