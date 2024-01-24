--View to get the evolution of the sales per month
CREATE OR REPLACE VIEW MonthlySalesEvolution AS
SELECT
    T.year AS "Ano",
    T.month AS "Mês",
    P.tipocultura AS "Tipo de cultura",
    SUM(V.quantidadePrimeiroAno) AS "Vendas do primeiro ano",
    SUM(V.quantidadeSegundoAno) AS "Vendas do segundo ano"
FROM VENDA V
INNER JOIN PRODUCT P
    ON V.idProduct = P.idProduct
INNER JOIN TEMPO T
    ON V.tempoID = T.tempoID
GROUP BY
    T.year,
    T.month,
    P.tipocultura
ORDER BY
    T.year,
    T.month,
    P.tipocultura;
    

--Demonstration    
SELECT * FROM MonthlySalesEvolution