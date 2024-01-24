CREATE OR REPLACE TRIGGER trg_check_dates
  BEFORE INSERT OR UPDATE ON COLHEITA
  FOR EACH ROW
BEGIN
  IF( :new.data_colheita > SYSDATE )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Invalid CloseDate: CloseDate must be greater than the current date - value = ' || 
          to_char( :new.data_colheita, 'YYYY-MM-DD HH24:MI:SS' ) );
  END IF;
END;

CREATE OR REPLACE TRIGGER trg_check_dates_operacao
  BEFORE INSERT OR UPDATE ON OPERACAO
  FOR EACH ROW
BEGIN
  IF( :new.data_prevista > :new.data_execucao )
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Invalid CloseDate: Data prevista must be lower than the Data Execucao ' );
  END IF;
END;