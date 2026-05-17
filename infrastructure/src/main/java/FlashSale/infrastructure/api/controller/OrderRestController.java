package FlashSale.infrastructure.api.controller;

import FlashSale.controller.OrderController;
import FlashSale.infrastructure.api.dto.PlaceOrderRequest;
import FlashSale.infrastructure.api.dto.PlaceOrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderController orderController; // Adapter
    private final OrderApiMapper mapper;

     // Aqui você pode adicionar os endpoints para criar, listar, atualizar e deletar produtos

    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest, UriComponentsBuilder uriComponentsBuilder) {
        log.info("[HTTP-IN] POST /products - Recebendo requisição para criar pedido");

        var input = mapper.toInput(placeOrderRequest);
        var output = orderController.create(input);

        var responseDTO = mapper.toResponse(output);

        var uri = uriComponentsBuilder.path("/products/{id}").buildAndExpand(responseDTO.orderId()).toUri();

        log.info("[HTTP-OUT] POST /products - Pedido criado com sucesso: orderId={}, status={}",
                responseDTO.orderId(), responseDTO.status());

        return ResponseEntity.accepted().location(uri).body(responseDTO);
    }
}
