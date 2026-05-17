package FlashSale.controller;

import FlashSale.domain.model.OrderStatus;
import FlashSale.usecase.PlaceOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    public OrderOutput create(OrderInput request) {
        var order = placeOrderUseCase.execute(request.customerId(), request.productId(), request.quantity());

        return new OrderOutput(order.getId(), order.getCustomerId(), order.getProductId(), order.getQuantity(), "Pedido criado com sucesso.", order.getStatus());
    }

    public record OrderInput(
            java.util.UUID customerId,
            java.util.UUID productId,
            Integer quantity
    ) {}

    public record OrderOutput(
            java.util.UUID id,
            java.util.UUID customerId,
            java.util.UUID productId,
            Integer quantity,
            String message,
            OrderStatus status
    ) {}
}
