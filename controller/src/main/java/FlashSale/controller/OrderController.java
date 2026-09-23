package FlashSale.controller;

import FlashSale.usecase.PlaceOrderUseCase;
import FlashSale.usecase.dto.OrderUseCaseOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final PlaceOrderUseCase placeOrderUseCase;

    public OrderOutput create(OrderInput request) {
        OrderUseCaseOutput useCaseOutput = placeOrderUseCase.execute(request.customerId(), request.productId(), request.quantity());

        return new OrderOutput(
                useCaseOutput.id(),
                useCaseOutput.customerId(),
                useCaseOutput.productId(),
                useCaseOutput.quantity(),
                "Pedido criado com sucesso.",
                useCaseOutput.status()
        );
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
            String status
    ) {}
}
