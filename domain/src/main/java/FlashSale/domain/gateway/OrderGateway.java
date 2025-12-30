package FlashSale.domain.gateway;

import FlashSale.domain.entity.Order;

public interface OrderGateway {
    void save(Order order);
}
