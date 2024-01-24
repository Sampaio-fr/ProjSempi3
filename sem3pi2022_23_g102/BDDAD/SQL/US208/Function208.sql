Create or REPLACE FUNCTION fnc208b(fatid in FATORES_DE_PRODUCAO.FATORES_ID%type , id_elem IN Elemento.id_elemento%type , quant IN FICHATECNICA.quantidade%type)
RETURN sys_refcursor
as
returnable sys_refcursor;
BEGIN
INSERT into FICHATECNICA
(FATORES_ID,ID_ELEMENTO,QUANTIDADE)
VALUES
(fatid,id_elem,quant);

OPEN returnable for
SELECT * FROM FICHATECNICA ft
Where ft.FATORES_ID = fatid
AND ft.ID_ELEMENTO = id_elem
AND ft.QUANTIDADE = quant;

return returnable;
END;
