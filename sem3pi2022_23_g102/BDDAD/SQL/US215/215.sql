 Create or REPLACE FUNCTION listOPA(data_ini in Operacao.data_prevista%type,data_fim in operacao.data_prevista%type)
	RETURN  Sys_REFCURSOR as 
    returnable SYS_REFCURSOR;

	BEGIN
	OPEN returnable for Select * FROM operacao opa
	Where data_ini<= opa.data_prevista AND data_fim>= opa.data_prevista;
	return returnable;
    END;
    
CREATE OR REPLACE PROCEDURE change_default_hub(client_id Cliente.codigo_interno%type, hub_id in Hub.loc_id%type)
AS
BEGIN
  UPDATE Cliente
  SET hub_id = hub_id
  WHERE  codigo_interno = client_id;
END;

CREATE OR REPLACE TRIGGER check_hub_before_insert
BEFORE INSERT ON Pedido
FOR EACH ROW
DECLARE
            v_loc_id hub.loc_id%type;
BEGIN
  SELECT h.loc_id
  INTO v_loc_id
  FROM Hub h
  WHERE h.loc_id = :new.hub_id;

  IF v_loc_id IS NULL THEN
    RAISE_APPLICATION_ERROR(-20001, 'Invalid hub ID');
  END IF;
END;