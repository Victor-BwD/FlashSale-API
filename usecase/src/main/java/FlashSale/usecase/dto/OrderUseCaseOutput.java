package FlashSale.usecase.dto;

import java.util.UUID;

public record OrderUseCaseOutput(
        UUID id,
        UUID customerId,
        UUID productId,
        Integer quantity,
        String status
) {}