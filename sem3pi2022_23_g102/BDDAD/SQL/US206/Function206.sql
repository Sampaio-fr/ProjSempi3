CREATE OR REPLACE FUNCTION fncUS206OrdenarParcelaPorDesignacao
    RETURN SYS_REFCURSOR AS
    returnable Sys_Refcursor;
BEGIN
    OPEN returnable for 
    SELECT * FROM parcela 
    ORDER BY designacao;
    return returnable;
end;

/

CREATE OR REPLACE FUNCTION fncUS206OrdenarParcelaPorArea(ord IN boolean DEFAULT true) 
    RETURN SYS_REFCURSOR AS
    returnable SYS_REFCURSOR;
BEGIN 
    if(ord=false) then
    OPEN returnable for
    SELECT * FROM parcela
    ORDER BY AREA DESC;
    ELSE
    OPEN returnable for
    SELECT * FROM PARCELA
    ORDER BY AREA ASC;
    end if;
    return returnable;
end;
   
  
/

CREATE OR REPLACE FUNCTION fncUS206OrdenarParcelaPorTipoDeCultura
    RETURN SYS_REFCURSOR AS
    returnable SYS_REFCURSOR;
BEGIN 
    OPEN returnable for
    SELECT * FROM parcela 
    ORDER BY tipo_cultura DESC;
    return returnable;
end;

/ 

