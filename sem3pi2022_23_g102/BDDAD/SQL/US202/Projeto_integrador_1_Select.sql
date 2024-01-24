SELECT codigo_interno, nrEncomendas_ultimoAno, valorEncomendas_ultimoAno, plafond, nivel, tipo_cliente, nr_Incidentes, nome, nif, email, ultimo_incidente, id_Morada_Correspondencia, id_Morada_Entrega 
  FROM Cliente;
SELECT colheitaID, data_colheita, gestor, codigo_cultura, id_parcela 
  FROM Colheita;
SELECT codigo_cultura, nome_Produto, tipo_cultura, preco, quantidade_Armazenada, preco_semente 
  FROM Cultura;
SELECT id_Data_Sensor, sensor_id, data, medicao, unidade 
  FROM Data_Sensor;
SELECT id_Elemento, unidade, categoria, substancia 
  FROM Elemento;
SELECT cod_estacao 
  FROM EstacaoMeteorologica;
SELECT fatores_id, aplicacao, classificacao, tipoDeFator, formulacao, nome_comercial, fornecedor 
  FROM Fatores_de_Producao;
SELECT fatores_id, id_parcela 
  FROM Fatores_de_Producao__Parcela;
SELECT fertilizacao_id, tecnica_agricola, data, quantidade, p_rega_id, fatores_id 
  FROM Fertilizacao;
SELECT fatores_id, id_Elemento, quantidade 
  FROM FichaTecnica;
SELECT id_incidente, id_pedido, codigo_interno, valor, data_incidente, data_liquidada 
  FROM Incidente;
SELECT id_Morada, codigo_Postal, rua, tipo_Morada, porta 
  FROM Morada;
SELECT id_parcela, designacao, area, tipo_cultura, p_rega_id 
  FROM Parcela;
SELECT id_parcela, codigo_cultura, quantidade_Semente, data_Semente 
  FROM Parcela_Cultura;
SELECT codigo_cultura, id_parcela, quant_colhida 
  FROM Parcela_Cultura_Colheita;
SELECT id_pedido, data, preco, data_pagamento_limite, estado_pagamento, estado_entrega, data_entrega, data_pagamento, data_entrega_prevista, codigo_interno, id_Morada 
  FROM Pedido;
SELECT id_pedido, codigo_cultura, quantidade 
  FROM Pedido_Cultura;
SELECT p_rega_id, tempos_de_rega, periodicidade, ordem_rega, data_rega, s_rega_id, cod_estacao 
  FROM PlanoDeRega;
SELECT sensor_id, tipo_sensor, unidade, medicao, valor_de_referencia, cod_estacao 
  FROM Sensor;
SELECT s_rega_id, qualidade_agua, dimensao, tipo_rega, quantidade_agua, unidade 
  FROM SistemaDeRega;
