package FlashSale.infrastructure.config;

import FlashSale.controller.OrderController;
import FlashSale.domain.gateway.InventoryGateway;
import FlashSale.domain.gateway.OrderGateway;
import FlashSale.usecase.PlaceOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public PlaceOrderUseCase placeOrderUseCase(InventoryGateway inventoryGateway, OrderGateway orderGateway) {
        return new PlaceOrderUseCase(inventoryGateway, orderGateway);
    }

    @Bean
    public OrderController orderController(PlaceOrderUseCase placeOrderUseCase) {
        return new OrderController(placeOrderUseCase);
    }
}
