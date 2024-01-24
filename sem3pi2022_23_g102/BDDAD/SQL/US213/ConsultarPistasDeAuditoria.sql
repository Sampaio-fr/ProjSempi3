--View to check pistas de auditoria
CREATE OR REPLACE VIEW pistasDeAuditoria AS
SELECT * FROM Auditoria WHERE id_parcela = 34141 AND login = 10 ORDER BY data_hora;


--Demonstration
SELECT * FROM pistasDeAuditoria;