--Create a view to check month sales evolution by hub and tipocultura
CREATE OR REPLACE VIEW monthSalesEvoHub AS
SELECT t.month, p.tipocultura, h.nome, SUM(v.quantidadePrimeiroAno) AS total_vendas
FROM VENDA v
INNER JOIN TEMPO t ON t.tempoID = v.tempoID
INNER JOIN PRODUCT p ON p.idProduct = v.idProduct
INNER JOIN HUB h ON h.hubID = v.hubID
GROUP BY t.month, p.tipocultura, h.nome;


--Demonstration
SELECT * FROM monthSalesEvoHub