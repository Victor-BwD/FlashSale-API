# language: pt
Funcionalidade: Realizar Pedido de Compra

  Cenário: Compra bem sucedida com estoque suficiente
    Dado que existe um produto com ID "550e8400-e29b-41d4-a716-446655440000" e 10 unidades no estoque
    Quando eu solicito a compra de 3 unidades desse produto
    Então um pedido deve ser gerado com status "PENDING"
    E o estoque do produto deve ser atualizado para 7