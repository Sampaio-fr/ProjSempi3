DELETE FROM Cliente 
  WHERE codigo_interno = ?;
DELETE FROM Colheita 
  WHERE colheitaID = ?;
DELETE FROM Cultura 
  WHERE codigo_cultura = ?;
DELETE FROM Data_Sensor 
  WHERE id_Data_Sensor = ?;
DELETE FROM Elemento 
  WHERE id_Elemento = ?;
DELETE FROM EstacaoMeteorologica 
  WHERE cod_estacao = ?;
DELETE FROM Fatores_de_Producao 
  WHERE fatores_id = ?;
DELETE FROM Fatores_de_Producao__Parcela 
  WHERE fatores_id = ? AND id_parcela = ?;
DELETE FROM Fertilizacao 
  WHERE fertilizacao_id = ?;
DELETE FROM FichaTecnica 
  WHERE fatores_id = ? AND id_Elemento = ?;
DELETE FROM Incidente 
  WHERE id_incidente = ?;
DELETE FROM Morada 
  WHERE id_Morada = ?;
DELETE FROM Parcela 
  WHERE id_parcela = ?;
DELETE FROM Parcela_Cultura 
  WHERE id_parcela = ? AND codigo_cultura = ?;
DELETE FROM Parcela_Cultura_Colheita 
  WHERE codigo_cultura = ? AND id_parcela = ?;
DELETE FROM Pedido 
  WHERE id_pedido = ?;
DELETE FROM Pedido_Cultura 
  WHERE id_pedido = ? AND codigo_cultura = ?;
DELETE FROM PlanoDeRega 
  WHERE p_rega_id = ?;
DELETE FROM Sensor 
  WHERE sensor_id = ?;
DELETE FROM SistemaDeRega 
  WHERE s_rega_id = ?;
