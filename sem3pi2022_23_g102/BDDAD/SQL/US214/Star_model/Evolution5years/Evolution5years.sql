--View to check the evolution of the production in the past 5 years
CREATE OR REPLACE VIEW EvolutionFiveYears AS
SELECT t.year, t.month, p.cultura, sum(pr.quantidade) as producao
FROM producao pr
INNER JOIN product p ON pr.idProduct = p.idProduct
INNER JOIN tempo t ON pr.tempoID = t.tempoID
INNER JOIN parcela pa ON pr.parcelaID = pa.parcelaID
WHERE p.cultura = '<cultura>' AND pa.nome = '<setor>'
GROUP BY t.year, t.month, p.cultura
HAVING t.year >= (SELECT MAX(year) FROM tempo) - 5
ORDER BY t.year, t.month;
    
--DEMONSTRATION    
SELECT * FROM EvolutionFiveYears
    
