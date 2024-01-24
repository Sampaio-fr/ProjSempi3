CREATE OR REPLACE TRIGGER trgOperacao
  BEFORE UPDATE OR DELETE                                       
  ON OPERACAO
  REFERENCING NEW AS NEW OLD AS OLD
  FOR EACH ROW
  DECLARE estado Operacao.ESTADO%TYPE;
  BEGIN
  IF DELETING THEN
    SELECT ESTADO INTO estado FROM Operacao WHERE OP_ID = :OLD.OP_ID;
    IF estado = 'PENDENTE' THEN
      UPDATE Operacao
      SET estado = 'CANCELADA'
      WHERE op_id = :OLD.OP_ID;
    END IF;
  END IF;

  IF UPDATING THEN
    SELECT ESTADO INTO estado FROM Operacao WHERE OP_ID = :OLD.OP_ID;
    IF estado = 'PENDENTE' THEN
      UPDATE OPERACAO
      SET tecnica = :NEW.tecnica,
          quant = :NEW.quant,
          data_prevista = :NEW.data_prevista,
          estado = 'CANCELADA',
          data_execucao = :NEW.data_execucao,
          tipoAplicacao = :NEW.tipoAplicacao
      WHERE OP_ID = :OLD.OP_ID;
    ELSIF estado = 'REALIZADA' THEN
      RAISE_APPLICATION_ERROR(-20007, 'N�o podemos alterar nem cancelar a opera��o');
    END IF;
  END IF;
END;

