CREATE OR REPLACE PROCEDURE US205updateClientLastYearInfo(codigoInterno IN cliente.codigo_interno%type
                                                            ) as


    novoNumero CLIENTE.NRENCOMENDAS_ULTIMOANO%type;
    novoValor  CLIENTE.VALORENCOMENDAS_ULTIMOANO%type;
    
BEGIN

    select count(id_pedido) into novoNumero from pedido where codigo_interno = codigoInterno and data = sysdate;
    
     select sum(preco) into novoValor from pedido where codigo_interno = codigoInterno and data = sysdate;

    UPDATE CLIENTE SET NRENCOMENDAS_ULTIMOANO = novoNumero, VALORENCOMENDAS_ULTIMOANO = novoValor WHERE CLIENTE.CODIGO_INTERNO = codigoInterno;
end;