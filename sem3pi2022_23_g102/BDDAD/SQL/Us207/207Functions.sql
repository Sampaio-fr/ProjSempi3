Create or REPLACE FUNCTION findSetorQuantBySafra(safra in Parcela.Tipo_Cultura%type)
RETURN  Sys_REFCURSOR as 
returnable SYS_REFCURSOR;

BEGIN
OPEN returnable for 
SELECT par.TIPO_CULTURA , pc.QUANT_COLHIDA/par.area as tonelada_por_hectares  
FROM Parcela par JOIN Parcela_cultura_colheita pc on par.ID_PARCELA=pc.ID_PARCELA
WHERE par.TIPO_CULTURA=safra
Order By tonelada_por_hectares DESC;
return returnable;
END;


 Create or REPLACE FUNCTION findSetorRentBySafra(safra in Parcela.Tipo_Cultura%type)
    RETURN  Sys_REFCURSOR as 
    returnable SYS_REFCURSOR;
    
    BEGIN
    OPEN returnable for 
    SELECT par.TIPO_CULTURA ,(pcc.QUANT_COLHIDA/par.area*cul.preco)-pc.quantidade_Semente*cul.preco_semente as lucro
    FROM Parcela par  JOIN Parcela_cultura_colheita pcc on par.ID_PARCELA=pcc.ID_PARCELA JOIN Parcela_Cultura pc on par.ID_PARCELA=pcc.ID_PARCELA JOIN Cultura cul on pcc.codigo_cultura=cul.codigo_cultura 
    WHERE par.TIPO_CULTURA=safra
    Order By lucro DESC;
    return returnable;
    END;



 

