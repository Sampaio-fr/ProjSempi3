CREATE TABLE CLIENTE
(
    codigo_interno number(10) GENERATED BY DEFAULT AS IDENTITY, 
    nome varchar2(255) NOT NULL, 
    nif number(10) NOT NULL UNIQUE CHECK(nif not like '%[^0-9]%' AND LENGTH(nif) = 9),
    email varchar2(255) NOT NULL UNIQUE CHECK(email like '%@%.%'), 
    PRIMARY KEY (codigo_interno)
);


CREATE TABLE PARCELA
(
    parcelaID    NUMBER(10, 0) NOT NULL,
    exploration NUMBER(10, 0) NOT NULL,
    nome        VARCHAR2(255) NOT NULL,
    PRIMARY KEY (parcelaID)
);

CREATE TABLE PRODUCT
(
    idProduct NUMBER(10, 0) NOT NULL,
    cultura      VARCHAR2(255) NOT NULL,
    tipocultura      VARCHAR2(255) NOT NULL,
    PRIMARY KEY (idProduct)
);


CREATE TABLE PRODUCAO
(
    producaoID NUMBER(10, 0) NOT NULL,
    tempoID       NUMBER(10, 0) NOT NULL,
    parcelaID     NUMBER(10, 0) NOT NULL,
    idProduct    NUMBER(10, 0) NOT NULL,
    quantidade   NUMBER(10, 0) NOT NULL,
    PRIMARY KEY (producaoID)
);



CREATE TABLE VENDA
(
    vendaID    NUMBER(10, 0) NOT NULL,
    tempoID    NUMBER(10, 0) NOT NULL,
    codigo_interno NUMBER(10, 0) NOT NULL,
    idProduct NUMBER(10, 0) NOT NULL,
    quantidadePrimeiroAno NUMBER(10, 0) NOT NULL,
    quantidadeSegundoAno NUMBER(10, 0) NOT NULL,
    PRIMARY KEY (vendaID)
);

CREATE TABLE TEMPO
(
    tempoID NUMBER(10, 0) NOT NULL PRIMARY KEY,
    year   NUMBER(4, 0)  NOT NULL,
    month  NUMBER(2, 0)  NOT NULL CHECK ( month BETWEEN 1 AND 12)
);




ALTER TABLE PRODUCAO
    ADD CONSTRAINT FKProducaoParcelaId FOREIGN KEY (parcelaID) REFERENCES PARCELA (parcelaID);
ALTER TABLE PRODUCAO
    ADD CONSTRAINT FKPRODUCAOProductId FOREIGN KEY (idProduct) REFERENCES PRODUCT (idProduct);
ALTER TABLE PRODUCAO
    ADD CONSTRAINT FKPRODUCAOTEMPOId FOREIGN KEY (tempoID) REFERENCES TEMPO (tempoID);   
ALTER TABLE VENDA
    ADD CONSTRAINT FKVendaClienteId FOREIGN KEY (codigo_interno) REFERENCES CLIENTE (codigo_interno);
ALTER TABLE VENDA
    ADD CONSTRAINT FKVendaProductId FOREIGN KEY (idProduct) REFERENCES PRODUCT (idProduct);
ALTER TABLE VENDA
    ADD CONSTRAINT FKVendaTempoId FOREIGN KEY (tempoID) REFERENCES TEMPO (tempoID);
   
    
    
    