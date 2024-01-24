set SERVEROUTPUT ON;

DECLARE
    cid NUMBER;
    codigopostalcorrespondencia   morada.codigo_postal%TYPE:=4520457;
    codigopostalentrega           morada.codigo_postal%TYPE:=5630168;
    ruacorrespondencia            morada.rua%TYPE:='rua2';
    ruaentrega                    morada.rua%TYPE:='ruaverde';
    tipocorrespondencia           morada.tipo_morada%TYPE:='CORRESPONDENCIA';
    tipoentrega                   morada.tipo_morada%TYPE:='ENTREGA';
    portacorrespondencia          morada.porta%TYPE:=81;
    portaentrega                  morada.porta%TYPE:=763;
BEGIN
    cid := us205criarcliente('email@isep.ipp.pt', codigopostalcorrespondencia, codigopostalentrega, ruacorrespondencia, ruaentrega,
                            tipocorrespondencia, tipoentrega, portacorrespondencia, portaentrega, 'particular','2022-05-22',
                            'André Luís Gomes',
                            269845157,
                            50000,
                            3,
                            10,
                            12500,
                            'B');

    dbms_output.put_line('New system user ID is ' || cid);
END;


DECLARE
    codigoInterno   pedido.codigo_interno%TYPE:=1;
    
BEGIN
    US205updateClientLastYearInfo(codigoInterno);

    
END;


DECLARE
    fatorRisco NUMBER;
    codigoInterno   pedido.codigo_interno%TYPE:=1;
BEGIN
    fatorRisco := clientriskfactor(codigoInterno);

    dbms_output.put_line('New system user ID is ' || fatorRisco);
END;