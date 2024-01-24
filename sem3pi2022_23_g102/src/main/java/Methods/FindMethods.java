package Methods;

import Company.Graph.Algorithms;
import Company.Graph.Edge;
import Company.Graph.map.MapGraph;
import Company.Graph.matrix.MatrixGraph;
import Company.Model.Cliente;
import Company.Model.Empresa;
import Company.Model.Pessoa;
import Company.Model.Produtor;
import Company.Store.MapGraphPessoaStore;
import Company.Store.StoreLists;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * The type Find methods.
 */
public class FindMethods {

    private final Comparator<Integer> comparator = Integer::compareTo;
    private final BinaryOperator<Integer> binaryOperator = Integer::sum;
    private StoreLists storeLists;
    private MapGraphPessoaStore mapGraphPessoaStore;

    /**
     * Instantiates a new Find methods.
     *
     * @param storeLists          the store lists
     * @param mapGraphPessoaStore the map graph pessoa store
     */
    public FindMethods(StoreLists storeLists, MapGraphPessoaStore mapGraphPessoaStore) {
        this.storeLists = storeLists;
        this.mapGraphPessoaStore = mapGraphPessoaStore;
    }


    //US305


    /**
     * It finds the minimum distance between all the vertices of a graph, and returns a map with the path and the distance
     *
     * @param graph The graph to be used.
     * @return A map with the minimum distance path and the minimum distance.
     */
    public Map<List<Pessoa>, Integer> findNetworkWithMinimumDistance(MapGraph<Pessoa, Integer> graph) {
        MatrixGraph<Pessoa, Integer> mg = Algorithms.minDistGraph(graph, comparator, binaryOperator);

        boolean[] visited;
        int sum, minDist = 0, sumMinDist = -1;
        List<Pessoa> minPath = new ArrayList<>();
        List<Pessoa> path;
        for (Pessoa pessoa : mg.vertices()) {
            path = new ArrayList<>();
            visited = new boolean[mg.vertices().size()];
            Pessoa vOrig = pessoa;
            path.add(vOrig);
            Pessoa vOrigAux = null;
            int counter = 1;
            sum = 0;
            while (counter < mg.vertices().size()) {
                minDist = -1;
                int vOrigKey = mg.key(vOrig);
                visited[vOrigKey] = true;
                for (Edge<Pessoa, Integer> edge : mg.outgoingEdges(vOrig)) {
                    Pessoa vDest = edge.getVDest();
                    int vDestKey = mg.key(edge.getVDest());
                    if (!visited[vDestKey]) {
                        if (edge.getWeight() < minDist || minDist < 0) {
                            minDist = edge.getWeight();
                            vOrigAux = vDest;
                        }
                    }
                }
                sum += minDist;
                vOrig = vOrigAux;
                path.add(vOrig);
                counter++;
            }
            if (sum < sumMinDist || sumMinDist < 0) {
                sumMinDist = sum;
                minPath = path;
            }
        }
        Map<List<Pessoa>, Integer> returnableMap = new HashMap<>();
        returnableMap.put(minPath, sumMinDist);

        return returnableMap;
    }//O(n^4)


    /**
     * It returns a list of all producers in the graph.
     *
     * @return A list of all producers in the graph.
     */
    public List<Produtor> findAllProducers() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();//O(1)
        ArrayList<Pessoa> auxList = graph.vertices();//O(V)
        List<Produtor> listProducers = new ArrayList<>();

        for (Pessoa pessoa : auxList) {//O(n)
            if (pessoa instanceof Produtor) {
                listProducers.add((Produtor) pessoa);
            }
        }
        return listProducers;
    }//O(n)


    /**
     * It gets the graph from the store, gets all the vertices, and then iterates through all the vertices to find the ones
     * that are companies
     *
     * @return A list of all the hubs in the graph.
     */
    public List<Empresa> findAllHubs() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        ArrayList<Pessoa> auxList = graph.vertices();//O(v)
        List<Empresa> listHubs = new ArrayList<>();

        for (Pessoa pessoa : auxList) {//O(n)
            if (pessoa instanceof Empresa) {
                listHubs.add((Empresa) pessoa);
            }
        }
        return listHubs;
    }//O(n)


    /**
     * It gets all the vertices of the graph, iterates over them and adds to a list only the ones that are instances of
     * Cliente or Empresa
     *
     * @return A list of all clients and companies.
     */
    public List<Pessoa> findAllClients() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        ArrayList<Pessoa> auxList = graph.vertices();//O(v)
        List<Pessoa> listClients = new ArrayList<>();

        for (Pessoa pessoa : auxList) {//O(n)
            if (pessoa instanceof Empresa || pessoa instanceof Cliente) {
                listClients.add(pessoa);
            }
        }
        return listClients;
    }//O(n)


    /**
     * It calculates the shortest path between each client and every other person in the graph
     */
    public void shortestPathEachClientToEverything() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        List<Pessoa> listClients = findAllClients();
        Map<Pessoa, Map<Pessoa, Integer>> pairIntegerMap = new HashMap<>();
        ArrayList<LinkedList<Pessoa>> paths = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();

        for (Pessoa client : listClients) {//O(n)
            boolean aux = Algorithms.shortestPaths(graph, client, comparator, binaryOperator, 0, paths, dists);
            pairIntegerMap.put(client, new HashMap<>());
            for (int i = 0; i < dists.size(); i++) {//O(n)
                if (paths.get(i).getLast() != client) {
                    pairIntegerMap.get(client).put(paths.get(i).getLast(), dists.get(i));
                }
            }
        }

        for (Map.Entry<Pessoa, Map<Pessoa, Integer>> map : pairIntegerMap.entrySet()) {//O(n)
            for (Map.Entry<Pessoa, Integer> minimap : map.getValue().entrySet()) {//O(n)
                System.out.print(map.getKey().getIdPessoa() + " -> ");
                System.out.println(minimap.getKey().getIdPessoa() + " : " + minimap.getValue());

            }
        }
    }//O(n^2)


//Produtores Proximo de cada hub


    /**
     * It finds the shortest path between every hub and every producer, and returns a map with the hubs as keys and a
     * treemap as values, where the treemap has the distance as key and the producer as value
     *
     * @return A map with the hubs as keys and a treemap with the distance to the closest producer and the producer as
     * value.
     */
    public Map<Pessoa, TreeMap<Integer, Produtor>> hubProducersClose() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        List<Empresa> listClients = findAllHubs();//O(n)
        Map<Pessoa, TreeMap<Integer, Produtor>> pessoaTreeMap = new HashMap<>();
        ArrayList<LinkedList<Pessoa>> paths = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();
        for (Pessoa client : listClients) {//O(n)
            Algorithms.shortestPaths(graph, client, comparator, binaryOperator, 0, paths, dists);
            pessoaTreeMap.put(client, new TreeMap<>());//O(logn)
            for (int i = 0; i < dists.size(); i++) {//O(n)
                if (paths.get(i).getLast() != client && paths.get(i).getLast() instanceof Produtor) {
                    pessoaTreeMap.get(client).put(dists.get(i), (Produtor) paths.get(i).getLast());//O(logn)
                }
            }
        }
        return pessoaTreeMap;

    }//O(n^2*logn)


    /**
     * For each hub, we get the nearest n producers and put them in a map
     *
     * @param auxMap a map with the hubs as keys and a TreeMap as values. The TreeMap has the distances as keys and the
     *               producers as values.
     * @param n      number of nearest producers to each hub
     * @return A map with the nearest producers for each hub.
     */
    public Map<Pessoa, Map<Produtor, Integer>> hubProducersN(Map<Pessoa, TreeMap<Integer, Produtor>> auxMap, int n) {
        Map<Pessoa, Map<Produtor, Integer>> nearestProducersEachHubMap = new HashMap<>();

        for (Pessoa client : auxMap.keySet()) {//O(n)
            Map<Produtor, Integer> nearestProducers = new HashMap<>();
            TreeMap<Integer, Produtor> sortedMap = auxMap.get(client);
            int i = 0;
            for (Integer distance : sortedMap.keySet()) {//O(n)
                if (i >= n) {
                    break;
                }
                nearestProducers.put(sortedMap.get(distance), distance);
                i++;
            }
            nearestProducersEachHubMap.put(client, nearestProducers);//O(logn)
        }
        return nearestProducersEachHubMap;
    }//O(n^2)


    //US303


    /**
     * It returns a map of clients and their respective companies, sorted by distance
     *
     * @return A map with the clients as keys and a treemap as values. The treemap has the distance as key and the company
     * as value.
     */
    public Map<Pessoa, TreeMap<Integer, Pessoa>> clientHub() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        List<Pessoa> listClients = findAllClients();//O(n)
        Map<Pessoa, TreeMap<Integer, Pessoa>> pessoaTreeMap = new HashMap<>();
        ArrayList<LinkedList<Pessoa>> paths = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();

        for (Pessoa client : listClients) {//O(n)
            Algorithms.shortestPaths(graph, client, comparator, binaryOperator, 0, paths, dists);
            pessoaTreeMap.put(client, new TreeMap<>());
            for (int i = 0; i < dists.size(); i++) {//O(n)
                if (paths.get(i).getLast() != client && paths.get(i).getLast() instanceof Empresa) {
                    pessoaTreeMap.get(client).put(dists.get(i), paths.get(i).getLast());//O(logn)
                }
            }
        }
        return pessoaTreeMap;
    }//O(n^2 * logn)


    /**
     * For each client, we get the sorted map of hubs and distances, and we add the first n hubs to a new map
     *
     * @param auxMap a map with the clients as keys and a TreeMap as values. The TreeMap has the distances as keys and the
     *               hubs as values.
     * @param n      number of nearest hubs to be returned
     * @return A map with the nearest hubs for each client.
     */
    public Map<Pessoa, Map<Pessoa, Integer>> clientHubN(Map<Pessoa, TreeMap<Integer, Pessoa>> auxMap, int n) {
        Map<Pessoa, Map<Pessoa, Integer>> nearestHubsMap = new HashMap<>();

        for (Pessoa client : auxMap.keySet()) {//O(n)
            Map<Pessoa, Integer> nearestHubs = new HashMap<>();
            TreeMap<Integer, Pessoa> sortedMap = auxMap.get(client);
            int i = 0;
            for (Integer distance : sortedMap.keySet()) {//O(n)
                if (i >= n) {
                    break;
                }
                nearestHubs.put(sortedMap.get(distance), distance);
                i++;
            }
            nearestHubsMap.put(client, nearestHubs);
        }
        return nearestHubsMap;
    }//O(n^2)


    /**
     * It finds the shortest path between every hub and every other node in the graph
     *
     * @return A map with the hubs as keys and a map with the closest pessoas to that hub as values.
     */
    public Map<Pessoa, Map<Pessoa, Integer>> closestHub() {
        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        List<Empresa> hubsList = findAllHubs();
        Map<Pessoa, Map<Pessoa, Integer>> pessoaMap = new HashMap<>();
        ArrayList<LinkedList<Pessoa>> paths = new ArrayList<>();
        ArrayList<Integer> dists = new ArrayList<>();

        for (Empresa hub : hubsList) {//O(n)
            Algorithms.shortestPaths(graph, hub, comparator, binaryOperator, 0, paths, dists);//O(n^2)
            pessoaMap.put(hub, new HashMap<>());
            for (int i = 0; i < dists.size(); i++) {//O(n)
                if (paths.get(i).getLast() != hub && (paths.get(i).getLast() instanceof Empresa || paths.get(i).getLast() instanceof Cliente)) {
                    pessoaMap.get(hub).put(paths.get(i).getLast(), dists.get(i));
                }
            }
        }
        return pessoaMap;
    }//O(n^3)

    /**
     * It iterates through the map of people and their closest hubs, and for each person it calculates the average distance
     * to their closest hubs, and then it returns the n people with the lowest average distance to their closest hubs
     *
     * @param n number of people to return
     * @return A list of the n closest hubs to the average of the distances to all other hubs.
     */
    public List<Pessoa> averageClosestHub(int n) {
        Map<Pessoa, Map<Pessoa, Integer>> map = closestHub();
        Map<Double, Pessoa> averageMap = new TreeMap<>();
        List<Pessoa> list = new ArrayList<>();

        int distance;
        double average;

        for (Pessoa pessoa : map.keySet()) {//O(n)
            Map<Pessoa, Integer> auxMap = map.get(pessoa);
            distance = 0;
            for (Map.Entry<Pessoa, Integer> entry : auxMap.entrySet()) {//O(n)
                distance += entry.getValue();
            }
            average = (double) distance / auxMap.size();
            averageMap.put(average, pessoa);
        }

        int i = 0;
        for (Map.Entry<Double, Pessoa> entry : averageMap.entrySet()) {//O(n)
            list.add(entry.getValue());
            i++;
            if (i >= n) {
                return list;
            }
        }

        return list;
    }//O(n^2)

}
