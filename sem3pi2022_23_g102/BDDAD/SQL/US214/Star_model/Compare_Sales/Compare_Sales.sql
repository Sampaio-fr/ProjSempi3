--Create a view to compare sales
CREATE OR REPLACE VIEW CompararVendas AS
SELECT T.MONTH                      as mes,
       P.CULTURA                    as NOME_PRODUTO,
       T.YEAR                   as PRIMEIRO_ANO,
       T2.YEAR                   as SEGUNDO_ANO,
       S1.quantidadePrimeiroAno               as VENDAS_PRIMEIRO_ANO,
       S2.quantidadeSegundoAno               as VENDAS_SEGUNDO_ANO,
       S1.quantidadePrimeiroAno - S2.quantidadeSegundoAno as COMPARACAO
FROM VENDA S1
         JOIN TEMPO T on S1.TEMPOID = T.TEMPOID
         JOIN PRODUCT P on P.IDPRODUCT = S1.IDPRODUCT,
     VENDA S2
         JOIN TEMPO T2 on T2.TEMPOID = S2.TEMPOID
WHERE T.MONTH = T2.MONTH
  AND S1.IDPRODUCT = S2.IDPRODUCT
  AND S1.codigo_interno = S2.codigo_interno;
  
  
--Demonstration
SELECT mes, NOME_PRODUTO, VENDAS_PRIMEIRO_ANO, VENDAS_SEGUNDO_ANO , COMPARACAO
FROM CompararVendas

  