package Company.Store;

import Company.Model.Pessoa;
import Company.Model.Produtor;
import Company.Model.Produtos;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Map store.
 */
public class MapStore {

    private Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> stockEcabazMap;

    private Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockProdutorMap;
    private Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> cabazMap;


    /**
     * Instantiates a new Map store.
     */
    public MapStore() {
        stockEcabazMap = new HashMap<>();
    }

    /**
     * Gets pessoa list map.
     *
     * @return the pessoa list map
     */
    public Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> getStockEcabazMap() {
        return stockEcabazMap;
    }

    /**
     * Sets pessoa list map.
     *
     * @param stockEcabazMap the pessoa list map
     */
    public void setStockEcabazMap(Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> stockEcabazMap) {
        this.stockEcabazMap = stockEcabazMap;
    }


    /**
     * Gets produtor list map.
     *
     * @return the produtor list map
     */
    public Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> getstockProdutorMap() {
        return stockProdutorMap;
    }

    /**
     * Sets produtor list map.
     *
     * @param stockProdutorMap the produtor list map
     */
    public void setstockProdutorMap(Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockProdutorMap) {
        this.stockProdutorMap = stockProdutorMap;
    }

    /**
     * Gets clientes list map.
     *
     * @return the clientes list map
     */
    public Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> getcabazMap() {
        return cabazMap;
    }

    /**
     * Sets clientes list map.
     *
     * @param cabazMap the clientes list map
     */
    public void setcabazMap(Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> cabazMap) {
        this.cabazMap = cabazMap;
    }
}
