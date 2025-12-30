import FlashSale.domain.entity.InventoryItem;
import FlashSale.domain.exception.StockInsufficientException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryItemTest {
    @Test
    void deveDiminuirEstoqueQuandoHouverSaldoSuficiente() {
        UUID productId = UUID.randomUUID();
        int estoqueInicial = 10;
        int quantidadeCompra = 4;
        InventoryItem item = new InventoryItem.Builder()
                .id(UUID.randomUUID())
                .productId(productId)
                .quantity(estoqueInicial)
                .build();

        item.decreaseStock(quantidadeCompra);

        assertEquals(6, item.getQuantity());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {
        // Arrange
        UUID productId = UUID.randomUUID();
        InventoryItem item = new InventoryItem.Builder()
                .id(UUID.randomUUID())
                .productId(productId)
                .quantity(2)
                .build();

        assertThrows(StockInsufficientException.class, () -> {
            item.decreaseStock(3);
        });
    }

    @Test
    void naoDeveAceitarQuantidadeNegativaOuZeroNaBaixa() {
        InventoryItem item = new InventoryItem.Builder()
                .id(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .quantity(5)
                .build();

        assertThrows(IllegalArgumentException.class, () -> item.decreaseStock(0));
        assertThrows(IllegalArgumentException.class, () -> item.decreaseStock(-1));
    }
}
