package Company.Controller;

import Company.Graph.map.MapGraph;
import Company.Model.*;
import Company.ReadFromCSV.ReadFromCSV;
import Company.Store.ExpedatingStore;
import Company.Store.MapGraphPessoaStore;
import Company.Store.MapStore;
import Company.Store.StoreLists;
import Company.WriteToFile.WriteToFile;
import Methods.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Main controller.
 */
public class MainController {

    private StoreLists storeLists;
    private MapGraphPessoaStore mapGraphPessoaStore;
    private Company company;
    private ReadFromCSV read;
    private FindMethods findMethods;
    private GraphConexo graphConexo;
    private CreateGraph createGraph;
    private MapStore mapStore;
    private CreateMap createMap;
    private Expedating expedating;
    private ExpedatingStore expedatingStore;
    private Statistics statistics;

    /**
     * Instantiates a new Main controller.
     */
    public MainController() {
        this.company = new Company();
        this.storeLists = company.getStoreLists();
        this.mapGraphPessoaStore = company.getMapGraphPessoaStore();
        this.mapStore = company.getMapStore();
        this.findMethods = new FindMethods(storeLists, mapGraphPessoaStore);
        this.graphConexo = new GraphConexo(mapGraphPessoaStore);
        this.createMap = new CreateMap(storeLists, mapStore);
        this.createGraph = new CreateGraph(storeLists);
        this.read = new ReadFromCSV(storeLists, createMap);
        this.expedatingStore = new ExpedatingStore(findMethods);
        this.expedating = new Expedating(mapGraphPessoaStore, mapStore, storeLists, findMethods, expedatingStore);
        this.statistics = new Statistics(findMethods, expedatingStore, expedating, mapStore);
    }
// Read From CSVS

    /**
     * It reads a file and returns a list of objects
     *
     * @param caminho the path of the file to be read
     * @return A list of objects of type Pessoa.
     * @throws FileNotFoundException the file not found exception
     */
    public List<Pessoa> lerClienteProdutor(String caminho) throws FileNotFoundException {
        return read.lerClienteProdutor(caminho);
    }


    /**
     * Sets client list.
     *
     * @param list the list
     */
    public void setClientList(List<Pessoa> list) {//O(1)
        storeLists.setPessoaList(list);
    }


    /**
     * It reads a file and returns a list of objects
     *
     * @param caminho the path to the file
     * @return A list of Helper objects.
     * @throws FileNotFoundException the file not found exception
     */
    public List<Helper> lerDistancias(String caminho) throws FileNotFoundException {
        return read.lerDistancias(caminho);
    }

    /**
     * Sets helper list.
     *
     * @param list the list
     */
    public void setHelperList(List<Helper> list) {//O(1)
        storeLists.setLocalidadeList(list);
    }

    /**
     * It reads a file and returns a list of objects
     *
     * @param caminho the path to the file you want to read
     * @return A list of Cabaz objects.
     * @throws Exception the exception
     */
    public void lerCabaz(String caminho) throws Exception {
        read.lerCabaz(caminho);
    }

    /**
     * Criar stock and cabaz.
     */
    public void criarStockAndCabaz() {
        createMap.createsMap();
        expedatingStore.setExpedatingList(new ArrayList<>());

    }

    /**
     * Expedating for day no restrictions.
     *
     * @param day the day
     * @return list
     */
    public List<Cabaz> expedatingForDayNoRestrictions(int day) {
        Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stock = mapStore.getstockProdutorMap();
        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> cabaz = mapStore.getcabazMap();
        List<Cabaz> aux = expedating.expedatingForDay(day, 0, 0, stock, cabaz);
        return aux;
    }

    /**
     * Expedatin for day restrictions.
     *
     * @param day the day
     * @param n   the n
     * @return list
     */
    public List<Cabaz> expedatingForDayRestrictions(int day, int n) {
        List<Cabaz> aux = expedating.expedatingForDay(day, 1, n, mapStore.getstockProdutorMap(), mapStore.getcabazMap());
        return aux;
    }

    /**
     * Path way map.
     *
     * @param day the day
     * @return the map
     */
    public Map<List<Pessoa>, Integer> pathWay(int day) {
        return expedating.expedatingPath(expedatingStore.getExpedatingList(), mapStore.getcabazMap(), day);
    }

//_____________________________________________________________________________________________

    /**
     * It creates a graph with the type of nodes and edges that we want.
     *
     * @return A MapGraph<Pessoa, Integer>
     */
    public MapGraph<Pessoa, Integer> createGraph() {//O(1)
        return createGraph.createGraph();
    }

    /**
     * Find pessoa by local pessoa.
     *
     * @param local the local
     * @return the pessoa
     */
    public Pessoa findPessoaByLocal(String local) {//O(1)
        return createGraph.findPessoaByLocal(local);
    }

    /**
     * It verifies if the graph is connected.
     *
     * @return The method verificarGraphConexo() is being returned.
     */
    public boolean verificarGraphConexo() {//O(1)

        return graphConexo.verificarGraphConexo();
    }

    /**
     * Find the nearest hub to the client.
     *
     * @return The client with the minimum distance from the hub.
     */
    public Map<List<Pessoa>, Integer> findNetworkWithMinimumDistance() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        if (graph == null || graph.vertices().size() == 0) {
            return null;
        }
        Map<List<Pessoa>, Integer> aux = findMethods.findNetworkWithMinimumDistance(graph);
        return aux;
    }

    /**
     * Find nearest hub to all clients list.
     *
     * @param n the n
     * @return the list
     */
    public List<Pessoa> findNearestHubToAllClients(int n) {//O(1)
        List<Pessoa> list = findMethods.averageClosestHub(n);
        return list;
    }

    /**
     * Find nearests hub each clients map.
     *
     * @param n the n
     * @return the map
     */
    public Map<Pessoa, Map<Pessoa, Integer>> findNearestsHubEachClients(int n) {//O(1)
        Map<Pessoa, Map<Pessoa, Integer>> nearestHubsMap = expedatingStore.hubEachClient(n);
        expedatingStore.setHubProximoCliente(nearestHubsMap);

        return nearestHubsMap;
    }
//__________________________________________________________________________________________________

    /**
     * It returns the number of connections of the graph's diameter
     *
     * @return The number of connections of the graph.
     */
    public int numeroligacoesDiam() {//O(1)
        //return graphConexo.numeroLigacoesDiam();
        return graphConexo.numeroLigacoesDiamOpcaoDois();
    }


    /**
     * > Sets the MapGraph object that will be used to store the graph data
     *
     * @param graph The graph to be stored.
     */
    public void setMapGraph(MapGraph<Pessoa, Integer> graph) {//O(1)
        mapGraphPessoaStore.setMapGraph(graph);
    }

    /**
     * This function reads the file "planosderega.csv" and stores the data in a list of Setor objects
     *
     * @param path the path to the file
     * @return A boolean value.
     * @throws Exception the exception
     */
    public boolean lerPlanosDeRega(String path) throws Exception {
        List<Setor> setorList = read.lerPlanosDeRega(path);
        storeLists.setSetorList(setorList);
        return WriteToFile.writeToFileMatrix(setorList, "planosderega.csv");
    }

    /**
     * This function verifies if the irrigation is possible in a certain sector, at a certain time
     *
     * @param setorList List of sectors
     * @param setor     The sector that you want to check if it's watering.
     * @param hora      LocalDateTime
     * @return A boolean value.
     */
    public boolean verificarRega(List<Setor> setorList, String setor, LocalDateTime hora) {
        MethodsRega methodsRega = new MethodsRega();
        return methodsRega.verificarRega(setorList, setor, hora);
    }

    /**
     * This function returns a list of all the setors in the database.
     *
     * @return A list of setor objects.
     */
    public List<Setor> getSetorList() {
        return storeLists.getSetorList();
    }

    /**
     * Print main statistics.
     */
    public void printMainStatistics() {
        statistics.printMainStatistics();
    }

    /**
     * Gets cabaz statistics.
     *
     * @return the cabaz statistics
     */
    public List<StatisticsByCabaz> getCabazStatistics() {
        return statistics.cabazStatistics();
    }

    /**
     * Gets expedition list statistics.
     *
     * @return the expedition list statistics
     */
    public ExpeditionStatistics getExpeditionListStatistics() {
        return statistics.expeditionStatistics(getCabazStatistics());
    }

    /**
     * Gets producer statistics.
     *
     * @return the producer statistics
     */
    public List<StatisticsByProdutor> getProducerStatistics() {
        return statistics.produtorStatistics(getCabazStatistics(), getExpeditionListStatistics());
    }

    /**
     * Gets hub statistics.
     *
     * @return the hub statistics
     */
    public List<StatisticsByHub> getHubStatistics() {
        return statistics.hubStatistics(getCabazStatistics(), getProducerStatistics());
    }
}
