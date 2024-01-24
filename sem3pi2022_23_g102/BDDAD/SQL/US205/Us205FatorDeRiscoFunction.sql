CREATE OR REPLACE FUNCTION clientriskfactor (
    codigointerno IN cliente.codigo_interno%TYPE
) RETURN NUMERIC AS
    lastyearincidentsvalue  incidente.valor%TYPE;
    lastincidentdate        incidente.data_incidente%TYPE;
    numeroporpagar          NUMERIC;
BEGIN
    SELECT
       sum(valor)
    INTO lastyearincidentsvalue
    FROM
        incidente
    WHERE
            codigo_interno = codigointerno
        AND data_incidente >= sysdate - INTERVAL '1' YEAR;

    SELECT
        data_incidente
    INTO lastincidentdate
    FROM
        incidente
    WHERE
        data_incidente = (
            SELECT
                MAX(data_incidente)
            FROM
                incidente);

    SELECT
        COUNT(id_pedido)
    INTO numeroporpagar
    FROM
        pedido
    WHERE
            pedido.data > lastincidentdate
        AND estado_pagamento = 0;


    
    return lastyearincidentsvalue/ numeroporpagar;
END;