set SERVEROUTPUT ON
declare
    fatoresid FATORES_DE_PRODUCAO.FATORES_ID%TYPE;
begin
    prcUS208ConfigurarFatoresDeProducao('semear', 'otimo', 'fert' ,'azz','fff','lidl',fatoresid);
    DBMS_OUTPUT.PUT_LINE('ID DOS FATORES:'|| fatoresid);
end;

/

declare
    elementid ELEMENTO.ID_ELEMENTO%TYPE;
begin
    prcUS208ConfigurarElemento('KG','substanciaorganica','organica' ,elementid);
    DBMS_OUTPUT.PUT_LINE('ID DO ELEMENTO:'|| elementid);
end;

/

declare   
    elem        ELEMENTO.id_elemento%TYPE;
    fatid       FATORES_DE_PRODUCAO.FATORES_ID%TYPE;
    quant       FICHATECNICA.QUANTIDADE%TYPE;
    fichatecn   FICHATECNICA%ROWTYPE;
    returnable Sys_Refcursor;
begin
    returnable := fnc208b(1,1,90);
    loop
        FETCH returnable into fichatecn;
        exit when returnable%notfound;
       DBMS_OUTPUT.PUT_LINE( ' FATOR ID:' || fichatecn.FATORES_ID  || 'ID DO ELEMENTO:'||fichatecn.ID_ELEMENTO || ' QUANTIDADE:' || fichatecn.QUANTIDADE);
    end loop;
end;

