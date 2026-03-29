package infrastructure.bdd.steps;

import FlashSale.domain.entity.InventoryItem;
import FlashSale.domain.entity.Order;
import FlashSale.domain.gateway.InventoryGateway;
import FlashSale.domain.gateway.OrderGateway;
import FlashSale.domain.model.OrderStatus;
import FlashSale.usecase.PlaceOrderUseCase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PurchaseProductSteps {
    private PlaceOrderUseCase placeOrderUseCase;
    private InventoryItem fakeInventoryInDatabase;
    private Order generatedOrder;
    private UUID fixedProductId;

    @Dado("que existe um produto com ID {string} e {int} unidades no estoque")
    public void setUpInventory(String uuidString, Integer initialQuantity) {
        this.fixedProductId = UUID.fromString(uuidString);
        this.fakeInventoryInDatabase = new InventoryItem.Builder()
                .id(UUID.randomUUID())
                .productId(this.fixedProductId)
                .quantity(initialQuantity)
                .build();

        InventoryGateway inventoryGatewayFake = new InventoryGateway() {
            @Override
            public java.util.Optional<InventoryItem> findByProductId(UUID productId) {
                if (productId.equals(fixedProductId)) {
                    return java.util.Optional.of(fakeInventoryInDatabase);
                }
                return java.util.Optional.empty();
            }

            @Override
            public void save(InventoryItem inventoryItem) {
                fakeInventoryInDatabase = inventoryItem; // Simulates inventory update
            }
        };

        OrderGateway orderGateway = new OrderGateway() {
            @Override
            public void save(Order order) {

            }
        };

        this.placeOrderUseCase = new PlaceOrderUseCase(inventoryGatewayFake, orderGateway);
    }

    @Quando("eu solicito a compra de {int} unidades desse produto")
    public void requestPurchase(Integer purchasedQuantity) {
        UUID customerId = UUID.randomUUID();

        this.generatedOrder = placeOrderUseCase.execute(customerId, this.fixedProductId, purchasedQuantity);
    }

    @Então("um pedido deve ser gerado com status {string}")
    public void verifyOrder(String expectedStatus) {
        assertNotNull(generatedOrder, "The order should have been generated, but it returned null");
        assertEquals(OrderStatus.valueOf(expectedStatus), generatedOrder.getStatus(), "The order status is incorrect");
    }

    @Então("O estoque do produto deve ser atualizado para {int}")
    public void verifyInventory(Integer expectedStockQuantity) {
        assertEquals(expectedStockQuantity, fakeInventoryInDatabase.getQuantity(), "The inventory quantity was not updated correctly");
    }
}

