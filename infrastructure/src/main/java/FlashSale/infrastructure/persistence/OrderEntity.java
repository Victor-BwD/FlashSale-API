package FlashSale.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders") // "order" é palavra reservada em SQL, sempre use no plural!
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    private UUID id;

    private UUID customerId;

    private UUID productId;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private FlashSale.domain.model.OrderStatus status;

    private LocalDateTime createdAt;
}
