
-- Procedure For Trigger--
CREATE OR REPLACE procedure inserir_auditorias
as
BEGIN
  INSERT INTO Auditoria (data_hora, tipo_alteracao, id_parcela, login, op_id)
  SELECT data_hora, tipo_alteracao, id_parcela, login, op_id FROM Auditoria_Temp;

  DELETE FROM Auditoria_Temp;
END;

--Trigger--
CREATE OR REPLACE TRIGGER gravar_auditorias
AFTER INSERT OR UPDATE OR DELETE ON operacao
BEGIN
  IF INSERTING THEN
    INSERT INTO Auditoria_Temp (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'INSERT', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)),(SELECT MAX(op_id) FROM operacao));
  ELSIF UPDATING THEN
    INSERT INTO Auditoria_Temp (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'UPDATE', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)), (SELECT MAX(op_id) FROM operacao));
  ELSIF DELETING THEN
    INSERT INTO Auditoria_Temp (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'DELETE', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)),(SELECT MAX(op_id) FROM operacao));
  END IF;
   inserir_auditorias;
END;


INSERT INTO Utilizador
    (
    id,
    email,
    password,
    data_login)
VALUES
    (10,
    'joao@gm.pt',
    'eeee33',
    '1970-01-01'); 
    
    
INSERT INTO Parcela
  (id_parcela,
  designacao,
  area,
  tipo_cultura,
  p_rega_id)
VALUES
  (34141,
  'gdfg',
  45,
  'PERMANENTE',
  4543);    

INSERT INTO Operacao
  (op_id,
  estado,
  quant,
  tecnica,
  data_prevista,
  data_execucao,
  tipoAplicacao,
  id_parcela)
  VALUES
  (10,
  'REALIZADA',
  9,
  'dcsfd',
  '2012-09-09',
  '2012-09-10',
  'fertirrega',
  34141);

---testing Trigger---
CREATE OR REPLACE TRIGGER gravar_auditoria
AFTER INSERT OR UPDATE OR DELETE ON operacao
FOR EACH ROW
BEGIN
  IF INSERTING THEN
    INSERT INTO Auditoria (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'INSERT', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)),(SELECT MAX(op_id) FROM operacao));
  ELSIF UPDATING THEN
    INSERT INTO Auditoria (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'UPDATE', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)), (SELECT MAX(op_id) FROM operacao));
  ELSIF DELETING THEN
    INSERT INTO Auditoria (data_hora, tipo_alteracao, id_parcela, login, op_id)
    VALUES (CURRENT_TIMESTAMP, 'DELETE', (SELECT MAX(id_parcela) FROM Parcela), (SELECT id FROM Utilizador WHERE data_login = (SELECT MAX(data_login) FROM Utilizador)),(SELECT MAX(op_id) FROM operacao));
  END IF;
END;


