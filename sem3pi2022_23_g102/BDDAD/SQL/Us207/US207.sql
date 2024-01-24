Set SERVEROUTPUT ON;
DECLARE
returnable sys_refcursor;
safra VARCHAR2(100);
harvest numeric;
Begin
     returnable :=findSetorQuantBySafra('PERMANENTE');
     Loop
        FETCH returnable into safra,harvest;
        EXIT WHEN returnable%notfound;
        DBMS_OUTPUT.PUT_LINE(safra || ' - ' || harvest);
        end loop;
end;

DECLARE
returnable sys_refcursor;
safra VARCHAR2(100);
harvest numeric;
Begin
     returnable :=findSetorRentBySafra('PERMANENTE');
     Loop
        FETCH returnable into safra,harvest;
        EXIT WHEN returnable%notfound;
        DBMS_OUTPUT.PUT_LINE(safra || ' - ' || harvest);
        end loop;
end;
