CREATE OR REPLACE TRIGGER trgNivelCliente
    BEFORE INSERT or UPDATE
    ON Cliente
    FOR EACH ROW
    
    declare
    
    numeroIncidentesAno number;
    valorEncomendasAno pedido.preco%type;
    codigoInterno cliente.codigo_interno%type;
    nivelNovo cliente.nivel%type;
    
BEGIN

    codigoInterno := :new.codigo_interno;

    SELECT
        count(*)
    INTO numeroIncidentesAno
    FROM
        incidente
    WHERE
            codigo_interno = codigoInterno
        AND data_incidente >= sysdate - INTERVAL '1' YEAR;
        
    SELECT
        sum(preco)
    INTO valorEncomendasAno
    FROM
        pedido
    WHERE
            codigo_interno = codigoInterno
        AND data >= sysdate - INTERVAL '1' YEAR;
        
        if(numberLastYearIncidents =>1)then
         nivelNovo := 'C';
         elsif(valorEncomendasAno=> 10000)then
         nivelNovo := 'A';
         elsif(valorEncomendasAno=> 5000)then
         nivelNovo := 'B';
         else
        nivelNovo := 'C';
         end if;
        
        :NEW.nivel := nivelNovo;
        
end;


drop trigger trgNivelCliente;
