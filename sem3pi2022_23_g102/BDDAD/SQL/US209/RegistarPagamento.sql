CREATE OR REPLACE PROCEDURE US209registarPagamento(codigoPedido IN pedido.id_pedido%type,
                                                    dataPagamento in pedido.data_Pagamento%type default null
                                                    ) as


    dataAtual pedido.data_entrega%type;
BEGIN

    dataAtual := SYSDATE;
    
    if(dataPagamento is null)then
    update pedido set data_entrega = dataAtual, ESTADO_PAGAMENTO = 1 where ID_PEDIDO = codigoPedido;
   else
   update pedido set data_pagamento = dataPagamento, ESTADO_PAGAMENTO = 1 where ID_PEDIDO = codigoPedido;
   end if;
end;