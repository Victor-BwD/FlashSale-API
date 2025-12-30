package FlashSale.domain.gateway;

import FlashSale.domain.entity.InventoryItem;

import java.util.Optional;
import java.util.UUID;

public interface InventoryGateway {
    Optional<InventoryItem> findByProductId(UUID productId);
    void save(InventoryItem inventoryItem);
}
