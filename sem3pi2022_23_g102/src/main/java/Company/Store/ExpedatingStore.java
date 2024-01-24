package Company.Store;

import Company.Model.Cabaz;
import Company.Model.Pessoa;
import Company.Model.Produtor;
import Methods.FindMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Expedating store.
 */
public class ExpedatingStore {


    private List<Cabaz> expedatingList;
    private Map<Pessoa, Map<Pessoa, Integer>> hubProximoCliente;
    private Map<Pessoa, Map<Produtor, Integer>> produtoresProximoHub;
    private FindMethods findMethods;


    /**
     * Instantiates a new Expedating store.
     *
     * @param findMethods the find methods
     */
    public ExpedatingStore(FindMethods findMethods) {
        this.findMethods = findMethods;
        this.expedatingList = new ArrayList<>();
    }

    /**
     * Produtores each hub map.
     *
     * @param n the n
     * @return the map
     */
    public Map<Pessoa, Map<Produtor, Integer>> produtoresEachHub(int n) {
        return produtoresProximoHub = findMethods.hubProducersN(findMethods.hubProducersClose(), n);
    }

    /**
     * Gets hub proximo cliente.
     *
     * @return the hub proximo cliente
     */
    public Map<Pessoa, Map<Pessoa, Integer>> getHubProximoCliente() {
        if (hubProximoCliente == null) {
            this.hubProximoCliente = findMethods.clientHubN(findMethods.clientHub(), 1);
        }
        return hubProximoCliente;
    }

    public void setHubProximoCliente(Map<Pessoa, Map<Pessoa, Integer>> map) {
        this.hubProximoCliente = map;
    }

    /**
     * Gets produtores proximo hub.
     *
     * @return the produtores proximo hub
     */
    public Map<Pessoa, Map<Produtor, Integer>> getProdutoresProximoHub() {
        return produtoresProximoHub;
    }

    /**
     * Hub each client map.
     *
     * @param n the n
     * @return the map
     */
    public Map<Pessoa, Map<Pessoa, Integer>> hubEachClient(int n) {
        return findMethods.clientHubN(findMethods.clientHub(), n);
    }

    /**
     * Produtor each hub map.
     *
     * @param n the n
     * @return the map
     */
    public Map<Pessoa, Map<Produtor, Integer>> produtorEachHub(int n) {
        return findMethods.hubProducersN(findMethods.hubProducersClose(), n);
    }

    /**
     * Gets expedating list.
     *
     * @return the expedating list
     */
    public List<Cabaz> getExpedatingList() {
        return expedatingList;
    }

    /**
     * Sets expedating list.
     *
     * @param expedatingList the expedating list
     */
    public void setExpedatingList(List<Cabaz> expedatingList) {
        this.expedatingList.addAll(expedatingList);
    }
}