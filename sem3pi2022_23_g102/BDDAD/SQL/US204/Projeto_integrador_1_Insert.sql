
INSERT INTO Utilizador
    (
    id,
    email,
    password,
    data_login)
VALUES
    (10,
    'joao@gm.pt',
    'eeee33',
    '1970-01-01'); 

INSERT INTO Morada
  (id_Morada,
  codigo_Postal,
  rua,
  tipo_Morada,
  porta)
VALUES
  (124,
  4564,
  45,
  'ENTREGA',
  78);
  
INSERT INTO Cliente
  (codigo_interno,
  nrEncomendas_ultimoAno,
  valorEncomendas_ultimoAno,
  plafond,
  nivel,
  tipo_cliente,
  nr_Incidentes,
  nome,
  nif,
  email,
  ultimo_incidente,
  id_Morada_Correspondencia,
  id_Morada_Entrega)
VALUES
  (0022,
  92,
  3500,
  10000,
  'A',
  'Particular',
  1,
  'Alex',
  345444444,
  'jrqrq@gmail.com',
  '2020-02-03',
  124,
  124);




INSERT INTO Cultura
  (codigo_cultura,
  nome_Produto,
  tipo_cultura,
  preco,
  quantidade_Armazenada,
  preco_semente)
VALUES
  (44,
  'pera',
  'PERMANENTE',
  2022,
  1001111,
  14);




INSERT INTO Elemento
  (id_Elemento,
  unidade,
  categoria,
  substancia)
VALUES
  (666,
  'kg',
  'abr',
  'abertua');


INSERT INTO EstacaoMeteorologica
  (cod_estacao)
VALUES
  (1614141);


INSERT INTO Sensor
  (sensor_id,
  tipo_sensor,
  unidade,
  medicao,
  valor_de_referencia,
  cod_estacao)
VALUES
  (55,
  'luz',
  'm',
  30,
  50,
  1614141);



INSERT INTO Data_Sensor
  (id_Data_Sensor,
  sensor_id,
  data,
  medicao,
  unidade)
VALUES
  (1999,
  55,
  '2021-02-08 15:48:09',
  82,
  'm');


  
INSERT INTO SistemaDeRega
  (s_rega_id,
  qualidade_agua,
  dimensao,
  tipo_rega,
  quantidade_agua,
  unidade)
VALUES
  (4141,
  'muito boa',
  42,
  'auto',
  45.0,
  'litros');  
  

INSERT INTO PlanoDeRega
  (p_rega_id,
  tempos_de_rega,
  periodicidade,
  ordem_rega,
  data_rega,
  s_rega_id,
  cod_estacao)
VALUES
  (4543,
  30,
  09,
  'primeiro',
  '2022-01-02 03_09:09',
  4141,
  1614141);

INSERT INTO Parcela
  (id_parcela,
  designacao,
  area,
  tipo_cultura,
  p_rega_id)
VALUES
  (34141,
  'gdfg',
  45,
  'PERMANENTE',
  4543);


INSERT INTO Operacao
  (op_id,
  estado,
  quant,
  tecnica,
  data_prevista,
  data_execucao,
  tipoAplicacao,
  id_parcela)
  VALUES
  (8,
  'REALIZADA',
  9,
  'dcsfd',
  '2012-09-09',
  '2012-09-10',
  'fertirrega',
  34141);  

    
INSERT INTO Fatores_de_Producao
  (fatores_id,
  aplicacao,
  classificacao,
  tipoDeFator,
  formulacao,
  nome_comercial,
  fornecedor,
  operacao)
VALUES
  (8,
  'regaru',
  'sustentavel',
  'bioquimica',
  'form',
  'Forma',
  'Aldi',
  8);



INSERT INTO FichaTecnica
  (fatores_id,
  id_Elemento,
  quantidade)
VALUES
  (8,
  666,
  45);





INSERT INTO Colheita
  (colheitaID,
  data_colheita,
  gestor)
VALUES
  (115151,
  '2020-03-19 09:09:09',
  'Jrge');




INSERT INTO AUDITORIA(
id_auditoria,
data_hora,
tipo_alteracao,
login,
op_id,
id_parcela)
VALUES
(003,
'2018-01-19 03:14:07',
'UPDATE',
10,
8,
34141);


INSERT INTO Parcela_Cultura
  (id_parcela,
  codigo_cultura,
  quantidade_Semente,
  data_Semente)
VALUES
  (34141,
  44,
  20,
  '2021-05-09');
  


INSERT INTO Parcela_Cultura_Colheita
  (codigo_cultura,
  id_parcela,
  colheitaID,
  quant_colhida)
VALUES
  (44,
  34141,
  115151,
  909796);


INSERT INTO Fatores_de_Producao__Parcela
  (fatores_id,
  id_parcela)
VALUES
  (8,
  34141);


INSERT INTO Fertilizacao
  (fertilizacao_id,
  tecnica_agricola,
  data,
  quantidade,
  p_rega_id,
  fatores_id)
VALUES
  (777143,
  'foice',
  '2011-05-22 16:10:10',
  565,
  4543,
  8);



INSERT INTO Pedido
  (id_pedido,
  data,
  preco,
  data_pagamento_limite,
  estado_pagamento,
  estado_entrega,
  data_entrega,
  data_pagamento,
  data_entrega_prevista,
  codigo_interno,
  id_Morada)
VALUES
  (0011,
  '2022-09-20',
  6011,
  '2022-09-22',
  1,
  'PAGA',
  '2022-09-02',
  '2009-02-04',
  '2008-08-08',
  0022,
  124);


INSERT INTO Pedido_Cultura
  (id_pedido,
  codigo_cultura,
  quantidade)
VALUES
  (0011,
  44,
  3041);

INSERT INTO Incidente
  (id_incidente,
  id_pedido,
  codigo_interno,
  valor,
  data_incidente,
  data_liquidada)
VALUES
  (45444,
  0011,
  0022,
  80,
  '2000-09-09',
  '2021-02-01');

