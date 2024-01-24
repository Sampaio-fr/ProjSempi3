set SERVEROUTPUT ON
declare
    parcelaid PARCELA.ID_PARCELA%TYPE;
begin
     prcUS206CriarParcela('df', 555, 'PERMANENTE', 334567, parcelaid );
     DBMS_OUTPUT.PUT_LINE('ID DA PARCELA:'|| parcelaid);
end;

/


declare   
    designation parcela.designacao%TYPE;
    parcelaid   parcela.id_parcela%TYPE;
    parcelaaa   PARCELA%rowtype;
    sectorCursor Sys_Refcursor;
begin
    sectorCursor := fncUS206OrdenarParcelaPorDesignacao;
    loop
        FETCH sectorCursor into parcelaaa;
        exit when sectorCursor%notfound;
       DBMS_OUTPUT.PUT_LINE( 'ID DA PARCELA:'||parcelaaa.ID_PARCELA ||' DESIGNACAO DA PARCELA:' || parcelaaa.DESIGNACAO);
    end loop;
end;
    
/





declare   
    area        parcela.area%TYPE;
    parcelaid   parcela.id_parcela%TYPE;
    parcelaaa   PARCELA%rowtype;
    sectorCursor Sys_Refcursor;
begin
    sectorCursor := fncUS206OrdenarParcelaPorArea;
    loop
        FETCH sectorCursor into parcelaaa;
        exit when sectorCursor%notfound;
        DBMS_OUTPUT.PUT_LINE( 'ID DA PARCELA:'||parcelaaa.ID_PARCELA ||' AREA DA PARCELA:' || parcelaaa.AREA);
    end loop;
end;

/


declare   
    tipcultura  parcela.tipo_cultura%TYPE;
    parcelaid   parcela.id_parcela%TYPE;
    parcelaaa   PARCELA%rowtype;
    sectorCursor Sys_Refcursor;
begin
    sectorCursor := fncUS206OrdenarParcelaPorTipoDeCultura;
    loop
        FETCH sectorCursor into parcelaaa;
        exit when sectorCursor%notfound;
        DBMS_OUTPUT.PUT_LINE( 'ID DA PARCELA:'||parcelaaa.ID_PARCELA ||' CULTURA DA PARCELA:' || parcelaaa.TIPO_CULTURA);
    end loop;
end;
