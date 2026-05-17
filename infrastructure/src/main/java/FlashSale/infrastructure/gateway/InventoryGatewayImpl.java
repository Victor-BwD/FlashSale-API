package FlashSale.infrastructure.gateway;

import FlashSale.domain.entity.InventoryItem;
import FlashSale.domain.gateway.InventoryGateway;
import FlashSale.infrastructure.persistence.InventoryEntity;
import FlashSale.infrastructure.persistence.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryGatewayImpl implements InventoryGateway {

    private final InventoryRepository inventoryRepository;

    @Override
    public Optional<InventoryItem> findByProductId(UUID productId) {
        Optional<InventoryEntity> entity = inventoryRepository.findByProductId(productId);

        return entity.map(e -> new InventoryItem.Builder()
                .id(e.getId())
                .productId(e.getProductId())
                .quantity(e.getQuantity())
                .build());
    }

    @Override
    public void save(InventoryItem inventoryItem) {
        InventoryEntity entity = InventoryEntity.builder()
                .id(inventoryItem.getId())
                .productId(inventoryItem.getProductId())
                .quantity(inventoryItem.getQuantity())
                .build();

        inventoryRepository.save(entity);
    }
}
