package FlashSale.infrastructure.gateway;

import FlashSale.domain.entity.Order;
import FlashSale.domain.gateway.OrderGateway;
import FlashSale.infrastructure.persistence.OrderEntity;
import FlashSale.infrastructure.persistence.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderGatewayImpl implements OrderGateway {
        private final OrderRepository orderRepository;

        @Override
        public void save(Order order) {
            OrderEntity entity = OrderEntity.builder()
                    .id(order.getId())
                    .productId(order.getProductId())
                    .quantity(order.getQuantity())
                    .build();

            orderRepository.save(entity);
        }
}
