INSERT INTO PRODUCT(idproduct, cultura, tipocultura)
VALUES (1, 'Morango', 'Permanente');
INSERT INTO PRODUCT(idproduct, cultura, tipocultura)
VALUES (2, 'Batata', 'Permanente');
INSERT INTO PRODUCT(idproduct, cultura, tipocultura)
VALUES (3, 'Cenoura', 'Temporaria');
INSERT INTO PRODUCT(idproduct, cultura, tipocultura)
VALUES (4, 'Banana', 'Temporaria');

INSERT INTO CLIENTE(codigo_interno, nome, nif, email)
VALUES (1,'simao' ,239745158, 'simao@gmail.com');
INSERT INTO CLIENTE(codigo_interno, nome, nif, email)
VALUES (2,'Rui' ,219743157, 'rui@gmail.com');
INSERT INTO CLIENTE(codigo_interno, nome, nif, email)
VALUES (3,'antonio' ,239735153, 'antonio@gmail.com');
INSERT INTO CLIENTE(codigo_interno, nome, nif, email)
VALUES (4,'ricardo', 332345456, 'ricardo@gmail.com');

INSERT INTO PARCELA(parcelaID, exploration, nome)
VALUES (1, 1, 'Carrot Field');
INSERT INTO PARCELA(parcelaID, exploration, nome)
VALUES (2, 2, 'Apple Field');
INSERT INTO PARCELA(parcelaID, exploration, nome)
VALUES (3,  3,  'Orange Field');
INSERT INTO PARCELA(parcelaID, exploration, nome)
VALUES (4, 4, 'Potato Field');


INSERT INTO TEMPO(tempoID,year,month)
VALUES (1,2014,4);
INSERT INTO TEMPO(tempoID,year,month)
VALUES (2,2015,5);
INSERT INTO TEMPO(tempoID,year,month)
VALUES (3,2016,10);
INSERT INTO TEMPO(tempoID,year,month)
VALUES (4,2020,12);

INSERT INTO PRODUCAO(producaoID, tempoID, parcelaID, idProduct, quantidade)
VALUES(1, 1, 1, 1, 10);
INSERT INTO PRODUCAO(producaoID, tempoID, parcelaID, idProduct, quantidade)
VALUES(2, 2, 2, 2, 20);
INSERT INTO PRODUCAO(producaoID, tempoID, parcelaID, idProduct, quantidade)
VALUES(3, 3, 3, 3, 30);
INSERT INTO PRODUCAO(producaoID, tempoID, parcelaID, idProduct, quantidade)
VALUES(4, 4, 4, 4, 40);

INSERT INTO VENDA(vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno, quantidadeSegundoAno)
VALUES(1, 1, 1, 1, 10, 5);
INSERT INTO VENDA(vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno, quantidadeSegundoAno)
VALUES(2, 2, 2, 2, 20, 5);
INSERT INTO VENDA(vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno, quantidadeSegundoAno)
VALUES(3, 3, 3, 3, 30, 3);
INSERT INTO VENDA(vendaID, tempoID, codigo_interno, idProduct, quantidadePrimeiroAno,quantidadeSegundoAno)
VALUES(4, 4, 4, 4, 40, 20);




