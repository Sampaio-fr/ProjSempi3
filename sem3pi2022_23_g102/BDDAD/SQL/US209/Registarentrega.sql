CREATE OR REPLACE PROCEDURE US209registarEntrega(codigoPedido IN pedido.id_pedido%type,
                                                    dataEntrega in pedido.data_entrega%type default null
                                                    ) as


    dataAtual pedido.data_entrega%type;
BEGIN

    dataAtual := SYSDATE;
    
    if(dataEntrega is null)then
    update pedido set data_entrega = dataAtual, ESTADO_ENTREGA = 'ENTREGUE' where ID_PEDIDO = codigoPedido;
   else
   update pedido set data_entrega = dataEntrega, ESTADO_ENTREGA = 'ENTREGUE' where ID_PEDIDO = codigoPedido;
   end if;
end;