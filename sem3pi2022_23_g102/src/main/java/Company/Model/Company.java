package Company.Model;

import Company.Store.MapGraphPessoaStore;
import Company.Store.MapStore;
import Company.Store.StoreLists;

/**
 * The type Company.
 */
public class Company {

    private StoreLists storeLists;
    private MapGraphPessoaStore mapGraphPessoaStore;

    private MapStore mapStore;

    /**
     * Instantiates a new Company.
     */
// Creating a new instance of the StoreLists and MapGraphPessoaStore classes.
    public Company() {
        this.storeLists = new StoreLists();
        this.mapGraphPessoaStore = new MapGraphPessoaStore();
        this.mapStore = new MapStore();
    }

    /**
     * Instantiates a new Company.
     *
     * @param storeLists          the store lists
     * @param mapGraphPessoaStore the map graph pessoa store
     * @param mapStore            the map store
     */
// A constructor that receives two parameters.
    public Company(StoreLists storeLists, MapGraphPessoaStore mapGraphPessoaStore, MapStore mapStore) {
        this.storeLists = storeLists;
        this.mapGraphPessoaStore = mapGraphPessoaStore;
        this.mapStore = mapStore;
    }


    /**
     * This function returns the storeLists object
     *
     * @return The storeLists object is being returned.
     */
    public StoreLists getStoreLists() {
        return storeLists;
    }

    /**
     * This function sets the storeLists variable to the storeLists parameter
     *
     * @param storeLists This is the list of stores that will be displayed in the list.
     */
    public void setStoreLists(StoreLists storeLists) {
        this.storeLists = storeLists;
    }

    /**
     * It returns the mapGraphPessoaStore object
     *
     * @return The mapGraphPessoaStore.
     */
    public MapGraphPessoaStore getMapGraphPessoaStore() {
        return mapGraphPessoaStore;
    }

    /**
     * > This function sets the mapGraphPessoaStore variable to the mapGraphPessoaStore parameter
     *
     * @param mapGraphPessoaStore This is the name of the MapGraphPessoaStore bean that we created in the previous step.
     */
    public void setMapGraphPessoaStore(MapGraphPessoaStore mapGraphPessoaStore) {
        this.mapGraphPessoaStore = mapGraphPessoaStore;
    }

    /**
     * Gets map store.
     *
     * @return the map store
     */
    public MapStore getMapStore() {
        return mapStore;
    }

    /**
     * Sets map store.
     *
     * @param mapStore the map store
     */
    public void setMapStore(MapStore mapStore) {
        this.mapStore = mapStore;
    }
}
