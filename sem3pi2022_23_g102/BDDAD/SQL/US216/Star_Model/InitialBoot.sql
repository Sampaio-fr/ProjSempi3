INSERT INTO HUB (hubID, tipoHub, nome) VALUES (1, 'F', 'Hub F');
INSERT INTO HUB (hubID, tipoHub, nome) VALUES (2, 'G', 'Hub G');

INSERT INTO CLIENTE (codigo_interno, nome, nif, email) VALUES (1, 'John Smith', 123456789, 'john@example.com');
INSERT INTO CLIENTE (codigo_interno, nome, nif, email) VALUES (2, 'Jane Doe', 987654321, 'jane@example.com');

INSERT INTO PARCELA (parcelaID, exploration, nome) VALUES (1, 1000, 'Parcela 1');
INSERT INTO PARCELA (parcelaID, exploration, nome) VALUES (2, 2000, 'Parcela 2');

INSERT INTO PRODUCT (idProduct, cultura, tipocultura) VALUES (1, 'maize', 'cereal');
INSERT INTO PRODUCT (idProduct, cultura, tipocultura) VALUES (2, 'wheat', 'cereal');

INSERT INTO TEMPO (tempoID, year, month) VALUES (1, 2021, 1);
INSERT INTO TEMPO (tempoID, year, month) VALUES (2, 2021, 2);


INSERT INTO PRODUCAO (producaoID, tempoID, parcelaID, idProduct, quantidade) VALUES (1, 1, 1, 1, 500);
INSERT INTO PRODUCAO (producaoID, tempoID, parcelaID, idProduct, quantidade) VALUES (2, 2, 2, 2, 1000);

INSERT INTO VENDA (vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno, quantidadeSegundoAno, hubID) VALUES (1, 1, 1, 1, 100, 200, 1);
INSERT INTO VENDA (vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno, quantidadeSegundoAno, hubID) VALUES (2, 2, 2, 2, 200, 300, 2);

