CREATE OR REPLACE PROCEDURE US209registarEncomenda(codigoInterno IN pedido.codigo_interno%type,
                                                    precoEncomenda in pedido.preco%type,
                                                    dataLimite in pedido.data_pagamento_limite%type,
                                                    codigopostalentrega          IN OUT  morada.codigo_postal%TYPE ,
                                                    ruaentrega                   IN OUT  morada.rua%TYPE ,
                                                    tipoentrega                  IN OUT  morada.tipo_morada%TYPE ,
                                                    portaentrega                 IN OUT  morada.porta%TYPE  ,
                                                    dataEntrega IN pedido.data_entrega_prevista%type) as


    moradaEntrega pedido.id_morada%type;
    dataAtual pedido.data%TYPE;
    estadoPagamento pedido.estado_pagamento%TYPE;
    estadoEntrega pedido.estado_entrega%TYPE;
    
BEGIN

    dataAtual := SYSDATE;
    
    IF ( codigopostalentrega IS NULL OR ruaentrega IS NULL OR tipoentrega IS NULL OR portaentrega IS NULL ) THEN
        select id_morada_entrega into moradaEntrega from cliente where codigo_interno = codigoInterno;
     ELSE
        INSERT INTO morada (
            codigo_postal,
            rua,
            tipo_morada,
            porta
        ) VALUES (
            codigopostalentrega,
            ruaentrega,
            tipoentrega,
            portaentrega
        ) RETURNING id_morada INTO moradaentrega;
     END IF;
     
     estadoPagamento := 0;
     
     estadoEntrega := 'REGISTADA';
    
    INSERT INTO Pedido
  ( 
  data, 
  preco, 
  data_pagamento_limite, 
  estado_pagamento, 
  estado_entrega, 
  data_entrega,
  data_pagamento,
  data_entrega_prevista,
  codigo_interno, 
  id_Morada) 
VALUES 
  ( 
  dataAtual, 
  precoEncomenda, 
  dataLimite, 
  estadoPagamento, 
  estadoEntrega, 
  null,
  null,
  dataEntrega,
  codigoInterno, 
  moradaentrega);

   
end;