CREATE OR REPLACE FUNCTION us205CriarCliente (
    clienteemail                 IN      cliente.email%TYPE,
    codigopostalcorrespondencia  IN OUT  morada.codigo_postal%TYPE,
    codigopostalentrega          IN OUT  morada.codigo_postal%TYPE,
    ruacorrespondencia           IN OUT  morada.rua%TYPE,
    ruaentrega                   IN OUT  morada.rua%TYPE,
    tipocorrespondencia          IN OUT  morada.tipo_morada%TYPE,
    tipoentrega                  IN OUT  morada.tipo_morada%TYPE,
    portacorrespondencia         IN OUT  morada.porta%TYPE,
    portaentrega                 IN OUT  morada.porta%TYPE,
    tipocliente                  IN      cliente.tipo_cliente%TYPE,
    ultimoincidente              IN      cliente.ultimo_incidente%TYPE,
    nome                         IN      cliente.nome%TYPE,
    clientenif                   IN      cliente.nif%TYPE,
    plafond                      IN      cliente.plafond%TYPE DEFAULT 500000,
    numeroincidentes             IN      cliente.nr_incidentes%TYPE DEFAULT 0,
    numeroencomendasano          IN      cliente.nrencomendas_ultimoano%TYPE DEFAULT 0,
    valorencomendasano           IN      cliente.valorencomendas_ultimoano%TYPE DEFAULT 0,
    nivel                        IN      cliente.nivel%TYPE
) RETURN cliente.codigo_interno%TYPE AS

    codigoreturninterno    cliente.codigo_interno%TYPE;
    moradacorrespondencia  morada.id_morada%TYPE;
    moradaentrega          morada.id_morada%TYPE;
    nullemail              cliente.email%TYPE;
BEGIN


    IF ( codigopostalcorrespondencia IS NULL OR ruacorrespondencia IS NULL OR tipocorrespondencia IS NULL OR portacorrespondencia
    IS NULL
    AND codigopostalentrega IS NULL OR ruaentrega IS NULL OR tipoentrega IS NULL OR portaentrega IS NULL ) THEN
        raise_application_error(-20003, 'Moradas nao podem ser nulas ou incompletas');
    END IF;

    IF ( codigopostalcorrespondencia IS NULL OR ruacorrespondencia IS NULL OR tipocorrespondencia IS NULL OR portacorrespondencia
    IS NULL ) THEN
        codigopostalcorrespondencia := codigopostalentrega;
        ruacorrespondencia := ruaentrega;
        tipocorrespondencia := tipoentrega;
        portacorrespondencia := portaentrega;
    ELSIF ( codigopostalentrega IS NULL OR ruaentrega IS NULL OR tipoentrega IS NULL OR portaentrega IS NULL ) THEN
        codigopostalentrega := codigopostalcorrespondencia;
        ruaentrega := ruacorrespondencia;
        tipoentrega := tipocorrespondencia;
        portaentrega := portacorrespondencia;
    END IF;

        INSERT INTO morada (
            codigo_postal,
            rua,
            tipo_morada,
            porta
        ) VALUES (
            codigopostalcorrespondencia,
            ruacorrespondencia,
            tipocorrespondencia,
            portacorrespondencia
        ) RETURNING id_morada INTO moradacorrespondencia;

   

   
        INSERT INTO morada (
            codigo_postal,
            rua,
            tipo_morada,
            porta
        ) VALUES (
            codigopostalentrega,
            ruaentrega,
            tipoentrega,
            portaentrega
        ) RETURNING id_morada INTO moradaentrega;


    INSERT INTO cliente (
        nrencomendas_ultimoano,
        valorencomendas_ultimoano,
        plafond,
        nivel,
        tipo_cliente,
        nr_incidentes,
        nome,
        nif,
        email,
        ULTIMO_INCIDENTE,
        id_morada_correspondencia,
        id_morada_entrega
    ) VALUES (
        numeroencomendasano,
        valorencomendasano,
        plafond,
        nivel,
        tipocliente,
        numeroincidentes,
        nome,
        clientenif,
        clienteemail,
        ultimoincidente,
        moradacorrespondencia,
        moradaentrega
    ) RETURNING codigo_interno INTO codigoreturninterno;

    COMMIT;
    RETURN codigoreturninterno;
EXCEPTION
WHEN DUP_VAL_ON_INDEX THEN
        RAISE_APPLICATION_ERROR(-20001, 'informação duplicada');
        return null;
    WHEN OTHERS THEN
        RAISE;
END;


