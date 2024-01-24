package Methods;

import Company.Model.*;
import Company.Store.MapStore;
import Company.Store.StoreLists;

import java.util.*;

import static Company.Constants.Constants.CLIENTEPRODUTOR_ID;
import static Company.Constants.Constants.DIA_CABAZES;

/**
 * The type Create map.
 */
public class CreateMap {

    private StoreLists storeLists;
    private MapStore mapStore;

    /**
     * Instantiates a new Creation map.
     *
     * @param storeLists the store lists
     * @param mapStore   the map store
     */
    public CreateMap(StoreLists storeLists, MapStore mapStore) {
        this.storeLists = storeLists;
        this.mapStore = mapStore;
    }


    /**
     * It takes a list of people, a map of people and a map of products and adds the products to the map of people
     *
     * @param values      String array with the values of the line
     * @param headerSplit String[]
     */
    public void guardarProducao(String[] values, String[] headerSplit) {
        List<Pessoa> pessoaList = storeLists.getPessoaList();
        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> mapClientes = mapStore.getStockEcabazMap();
        Map<Produtos, Double> mapProdutos = createMapFruta(values, headerSplit);
        TreeMap<Integer, Map<Produtos, Double>> mapAuxiliar;

        for (Pessoa pessoaAuxiliar : pessoaList) {//O(n)
            if (pessoaAuxiliar.getIdPessoa().equals(values[CLIENTEPRODUTOR_ID].replaceAll("\"", " ").trim())) {
                if (mapClientes.containsKey(pessoaAuxiliar)) {
                    TreeMap<Integer, Map<Produtos, Double>> mapFruta = mapClientes.get(pessoaAuxiliar);
                    mapFruta.put(Integer.parseInt(values[DIA_CABAZES]), mapProdutos);//O(logn)
                } else {
                    mapAuxiliar = new TreeMap<>();
                    mapAuxiliar.put(Integer.parseInt(values[DIA_CABAZES]), mapProdutos);//O(logn)
                    mapClientes.put(pessoaAuxiliar, mapAuxiliar);//O(logn)
                }
            }
        }
    }//O(n*logn)


    /**
     * It creates a map of products and their respective values
     *
     * @param values      the values of the line
     * @param headerSplit The header of the CSV file, which is the first line.
     * @return A map with the products and the quantity of each product.
     */
    private Map<Produtos, Double> createMapFruta(String[] values, String[] headerSplit) {
        Map<Produtos, Double> mapProdutos = new HashMap<>();
        Produtos auxProdutos;

        for (int i = 2; i < headerSplit.length; i++) {//O(n)
            auxProdutos = new Produtos(headerSplit[i].replaceAll("\"", " ").trim());
            if (Double.parseDouble(values[i]) != 0) {
                mapProdutos.put(auxProdutos, Double.parseDouble(values[i]));//O(logn)
            }
        }
        return mapProdutos;
    }//O(nlogn)


    /**
     * This function creates two maps, one for the stock and one for the cabaz.
     */
    public void createsMap() {
        createStockMap();//O(nlogn)
        createCabazMap();//O(nlogn)
    }//O(nlogn)


    /**
     * It creates a new map with the same data as the old one, but with a different key type
     */
    private void createStockMap() {
        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> auxMap = mapStore.getStockEcabazMap();
        Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockMap = new HashMap<>();
        Set<Pessoa> pessoasKey = auxMap.keySet();
        Produtor produtorAuxiliar;

        for (Pessoa pessoaAux : pessoasKey) {//O(n)
            if (pessoaAux instanceof Produtor) {
                produtorAuxiliar = (Produtor) pessoaAux;
                stockMap.put(produtorAuxiliar, auxMap.get(pessoaAux));//O(logn)
            }
        }
        mapStore.setstockProdutorMap(stockMap);
    }//O(nlogn)


    /**
     * It creates a new map with the same keys as the original map, but only the values that are instances of Cliente or
     * Empresa
     */
    private void createCabazMap() {
        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> auxMap = mapStore.getStockEcabazMap();
        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> cabazMap = new HashMap<>();
        Set<Pessoa> pessoasKey = auxMap.keySet();

        for (Pessoa pessoaAux : pessoasKey) {//O(n)
            if (pessoaAux instanceof Cliente || pessoaAux instanceof Empresa) {
                cabazMap.put(pessoaAux, auxMap.get(pessoaAux));//O(logn)
            }
        }
        mapStore.setcabazMap(cabazMap);
    }//O(nlogn)


}
