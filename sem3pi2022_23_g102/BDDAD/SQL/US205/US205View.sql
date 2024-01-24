CREATE OR REPLACE VIEW ClienteView AS 
SELECT codigo_interno                                                          AS "Codigo Interno",
       nome                                                        AS "Nome",
       nivel                                                AS "Nivel",
      COALESCE(to_char(ultimo_incidente), 'Sem incidentes à data')  AS "Ultimo incidente",
       (SELECT count(*)
        FROM pedido P
        WHERE TabelaCliente.codigo_interno = P.CODIGO_INTERNO
          AND P.ESTADO_PAGAMENTO = 1
          AND P.data > SYSDATE - 365)                         AS "Numero encomendas pagas",
       (SELECT count(*)
        FROM pedido
        WHERE CODIGO_INTERNO = TabelaCliente.codigo_interno
          AND estado_entrega = 'entregue'
          AND ESTADO_PAGAMENTO = 0)                                         AS "Numero encomendas por pagar"
FROM CLIENTE TabelaCliente;