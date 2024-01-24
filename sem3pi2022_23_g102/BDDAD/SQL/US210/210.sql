Create or REPLACE FUNCTION registeroperation(tcn in operacao.Tecnica%type , data_prev in operacao.data_prevista%type,data_exe in operacao.data_prevista%type,quant in operacao.quant%type, tipoapp in operacao.tipoAplicacao%type, opera_id in operacao.op_id%type)
    RETURN  Sys_REFCURSOR as 
    returnable SYS_REFCURSOR;
    
    BEGIN
   Insert INTO Operacao
   (op_ID,tecnica,data_prevista,data_execucao,quant,tipoAplicacao)
   VALUES(
   opera_id ,tcn,data_prev,data_exe,quant,tipoapp);
   Open returnable for
   Select op_ID from operacao  op
   Where op.op_ID=opera_id ;
    return returnable;
    END;
    

---------------------------------210.2-----------------------------------------
CREATE OR REPLACE TRIGGER check_restrictions
BEFORE INSERT ON Operacao
FOR EACH ROW
DECLARE 
            restricao_quantidade restricao.quantidade%type;
            restricao_data_fim restricao.data_FIM%type;
            restricao_data_inicio restricao.data_INICIO%type;
            opa_quant operacao.QUANT%type;
            opa_data operacao.data_prevista%type;
BEGIN  
    SELECT r.quantidade INTO restricao_quantidade
    FROM Restricao r INNER JOIN Parcela p ON p.id_parcela = r.id_parcela INNER JOIN Operacao op ON op.id_parcela = p.id_parcela;
    
    SELECT r.data_fim INTO restricao_data_fim
    FROM Restricao r
    INNER JOIN Parcela p ON p.id_parcela = r.id_parcela
    INNER JOIN Operacao op ON op.id_parcela = p.id_parcela;
  
    SELECT r.data_inicio INTO restricao_data_inicio
    FROM Restricao r
    INNER JOIN Parcela p ON p.id_parcela = r.id_parcela
    INNER JOIN Operacao op ON op.id_parcela = p.id_parcela;
    
    
  
    IF (opa_quant> restricao_quantidade) OR (opa_data> restricao_data_fim) OR (opa_data< restricao_data_inicio) THEN
         RAISE_APPLICATION_ERROR(-20007, 'Restrições não respeitadas');
    END IF;
END;
    
    
---------------------------------210.3-----------------------------------------
CREATE OR REPLACE TRIGGER  check_restrictions_week
BEFORE INSERT OR UPDATE ON operacao
FOR EACH ROW
DECLARE restriction_exists INT;
BEGIN
  SELECT COUNT(*) INTO restriction_exists
  FROM Restricao r
    INNER JOIN Parcela p ON p.id_parcela = r.id_parcela
    INNER JOIN Operacao op ON op.id_parcela = p.id_parcela
   WHERE op.data_prevista = op.data_prevista-7;
  
  -- If there are restrictions, cancel the insert or update operation
  IF (restriction_exists > 0) THEN  
     RAISE_APPLICATION_ERROR(-20007, 'Operação não pode efetuada');
  END IF;
END;



---------------------------------210.4-----------------------------------------
    Create or REPLACE FUNCTION listRestrictions(parcelaID in PARCELA.id_parcela%type , fatoresID in FATORES_DE_PRODUCAO.fatores_id%type,datas in operacao.data_prevista%type)
    RETURN  Sys_REFCURSOR as 
    returnable SYS_REFCURSOR;
    
    BEGIN
    OPEN returnable for 
    SELECT res.id_restricao , res.descricao 
    FROM Restricao res JOIN fatores_de_producao fp on res.fatores_id=fp.fatores_id Join Parcela par on par.id_parcela=res.id_parcela Join operacao opa on opa.op_id=fp.operacao
    WHERE par.id_parcela=parcelaID AND fp.fatores_id=fatoresID AND opa.data_prevista=datas;
    return returnable;
    END;
---------------------------------210.5-----------------------------------------
 Create or REPLACE FUNCTION listOPA(data_ini in Operacao.data_prevista%type,data_fim in operacao.data_prevista%type)
	RETURN  Sys_REFCURSOR as 
    returnable SYS_REFCURSOR;

	BEGIN
	OPEN returnable for 
	Select * FROM operacao opa
	Where data_ini<= opa.data_prevista AND data_fim>= opa.data_prevista;
	return returnable;
    END;
    
    -----------------------------------------------------------------------


