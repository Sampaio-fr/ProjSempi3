DECLARE
    codigointerno   pedido.codigo_interno%TYPE:=0022;
    precoEncomenda           pedido.preco%TYPE:=20000;
    dataLimite            pedido.data_pagamento_limite%TYPE:='2022-12-10';
    codigopostalentrega                    morada.codigo_postal%TYPE:=null;
    ruaentrega           morada.rua%TYPE:='rua54';
    tipoentrega                   morada.tipo_morada%TYPE:='ENTREGA';
    portaentrega                  morada.porta%TYPE:=763;
    dataEntrega                   pedido.data_entrega_prevista%type := '2022-12-20';
BEGIN
    US209registarEncomenda(codigointerno, precoEncomenda, dataLimite, codigopostalentrega, ruaentrega, tipoentrega, portaentrega, dataEntrega);

    
END;


DECLARE
    codigoPedido   pedido.id_pedido%TYPE:=1;
    dataEntrega            pedido.data_entrega%TYPE:='2022-12-30';
BEGIN
    US209registarEntrega(codigoPedido, dataEntrega);

    
END;


DECLARE
    codigoPedido   pedido.id_pedido%TYPE:=1;
    dataPagamento            pedido.data_pagamento%TYPE:='2022-12-30';
BEGIN
    US209registarPagamento(codigoPedido, dataPagamento);

    
END;