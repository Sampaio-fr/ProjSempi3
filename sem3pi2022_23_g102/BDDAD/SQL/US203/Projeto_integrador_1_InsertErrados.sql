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
  (001,
  90,
  35,
  100,
  'A',
  'Particular',
  7,
  999,
  345666,
  'jorgegmailcom',
  '2020-09-03',
  12553000,
  12553);
/*O email não contem os caracteres especiais e o nif não tem lenght de 9*/
  
INSERT INTO Colheita
  (colheitaID,
  data_colheita,
  gestor)
VALUES
  (1234,
  '2022-12-05 12:12:09',
  'joao');
/* A data da colheita ultrapassa a data atual*/

INSERT INTO Cultura
  (codigo_cultura,
  nome_Produto,
  tipo_cultura,
  preco,
  quantidade_Armazenada,
  preco_semente)
VALUES
  (90908,
  'maca',
  'cdsf',
  20,
  100,
  90);
/* O tipo de cultura não corresponde aos tipos pedidos*/

INSERT INTO Data_Sensor
  (id_Data_Sensor,
  sensor_id,
  data,
  medicao,
  unidade)
VALUES
  (99888,
  99,
  '2021-09-08 15:48:09',
  82,
  'kg');
/* Pode ser criada pois não há restrições em si mesma, nem na tabela que recebe*/

INSERT INTO Elemento
  (id_Elemento,
  unidade,
  categoria,
  substancia)
VALUES
  (333,
  44,
  'g',
  'azoto');
/* Pode ser criada pois não há restrições em si mesma*/
INSERT INTO EstacaoMeteorologica
  (cod_estacao)
VALUES
  (66689);
/* Pode ser criada pois não há restrições em si mesma*/

INSERT INTO Fatores_de_Producao
  (fatores_id,
  aplicacao,
  classificacao,
  tipoDeFator,
  formulacao,
  nome_comercial,
  fornecedor)
VALUES
  (665,
  'fdsgd',
  'pskifd',
  'dvfghtr',
  'form',
  'dsfg',
  'lidl');
/* Pode ser criada pois não há restrições em si mesma*/

INSERT INTO Fatores_de_Producao__Parcela
  (fatores_id,
  id_parcela)
VALUES
  (665,
  335);
/* Não pode ser criada devido à restrição da table da parcela */

INSERT INTO Fertilizacao
  (fertilizacao_id,
  tecnica_agricola,
  data,
  quantidade,
  p_rega_id,
  fatores_id)
VALUES
  (4627,
  55,
  '2020-01-01 09:10:10',
  56,
  334567,
  666);
/* Não pode ser criada pois há restrições na tabela do plano de rega(não foi criada)*/

INSERT INTO FichaTecnica
  (fatores_id,
  id_Elemento,
  quantidade)
VALUES
  (665,
  333,
  45);
/* Pode ser criada pois não há restrições em si mesma, nem nas tabelas que recebe*/

INSERT INTO Incidente
  (id_incidente,
  id_pedido,
  codigo_interno,
  valor,
  data_incidente,
  data_liquidada)
VALUES
  (43,
  0054,
  001,
  80,
  '2021-09-09',
  '2022-02-01');
/* Não pode ser criado pois há restrições na tabela do Pedido, que o Incidente recebe*/


INSERT INTO Morada
  (id_Morada,
  codigo_Postal,
  rua,
  tipo_Morada,
  porta)
VALUES
  (234,
  4564,
  45,
  'fdg',
  78);
/* Não pode ser criada pois há restrições em si mesma(tipo_morada)*/

INSERT INTO Parcela
  (id_parcela,
  designacao,
  area,
  tipo_cultura,
  p_rega_id)
VALUES
  (335,
  'gdfg',
  45,
  'PE',
  334567);
/* Não pode ser criada pois há restrições em si mesma(tipo_cultura)*/

INSERT INTO Parcela_Cultura
  (id_parcela,
  codigo_cultura,
  quantidade_Semente,
  data_Semente)
VALUES
  (335,
  90908,
  'aaaaa',
  '2020-05-09');
/* Não pode ser criada devido ao id_parcela e ao codigo_cultura que pertencem a tabelas que não podem ser criadas*/

INSERT INTO Parcela_Cultura_Colheita
  (codigo_cultura,
  id_parcela,
  colheitaID,
  quant_colhida)
VALUES
  (90908,
  335,
  1234,
  90);
/* Não pode ser criada devido ao id_parcela e ao codigo_cultura que pertencem a tabelas que não podem ser criadas*/


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
  (54,
  '2021-09-20',
  888,
  '2022-03-20',
  2,
  'PAGAaaaa',
  '2022-09-02',
  '2009-02-04',
  '2008-08-08',
  001,
  1234);
/* Não pode ser criada devido ao estado_entrega e ao codigo_interno pertecente a uma table não criada*/

INSERT INTO Pedido_Cultura
  (id_pedido,
  codigo_cultura,
  quantidade)
VALUES
  (54,
  90908,
  66);
/* Não pode ser criada, porque recebe tabelas com restrições*/

INSERT INTO PlanoDeRega
  (p_rega_id,
  tempos_de_rega,
  periodicidade,
  ordem_rega,
  data_rega,
  s_rega_id,
  cod_estacao)
VALUES
  (567,
  30,
  09,
  'primeiro',
  '2022-01-02 03_09:09',
  07,
  66689);
/* Não pode ser criada, pois recebe uma tabela com restrições */


INSERT INTO Sensor
  (sensor_id,
  tipo_sensor,
  unidade,
  medicao,
  valor_de_referencia,
  cod_estacao)
VALUES
  (99,
  'luz',
  67,
  30,
  50,
  66689);
/*Pode ser criada, pois não tem restrições nem recebem tabelas com restrições*/

INSERT INTO SistemaDeRega
  (s_rega_id,
  qualidade_agua,
  dimensao,
  tipo_rega,
  quantidade_agua,
  unidade)
VALUES
  (07,
  'muuuito boa',
  46,
  'auto',
  56,
  'litros');
/* Não pode ser criada, devido à restrição  da qualidade_agua*/