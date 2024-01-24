package Methods;

import Company.Graph.Algorithms;
import Company.Graph.Edge;
import Company.Graph.map.MapGraph;
import Company.Graph.matrix.MatrixGraph;
import Company.Model.*;
import Company.Store.ExpedatingStore;
import Company.Store.MapGraphPessoaStore;
import Company.Store.MapStore;
import Company.Store.StoreLists;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * The type Expedating.
 */
public class Expedating {

    private final Comparator<Integer> comparator = Integer::compareTo;
    private final BinaryOperator<Integer> binaryOperator = Integer::sum;
    private MapGraphPessoaStore mapGraphPessoaStore;
    private MapStore mapStore;
    private StoreLists storeLists;
    private FindMethods findMethods;
    private ExpedatingStore expedatingStore;

    /**
     * Instantiates a new Expedating.
     *
     * @param mapGraphPessoaStore the map graph pessoa store
     * @param mapStore            the map store
     * @param storeLists          the store lists
     * @param findMethods         the find methods
     * @param expedatingStore
     */
    public Expedating(MapGraphPessoaStore mapGraphPessoaStore, MapStore mapStore, StoreLists storeLists, FindMethods findMethods, ExpedatingStore expedatingStore) {
        this.mapGraphPessoaStore = mapGraphPessoaStore;
        this.mapStore = mapStore;
        this.storeLists = storeLists;
        this.findMethods = findMethods;
        this.expedatingStore = expedatingStore;
    }


    //US308/309

    /**
     * This function is responsible for creating the expedating list, which is a list of all the products that will be
     * delivered to the clients
     *
     * @param day         the day of the week
     * @param restriction 0 for no restriction, 1 for restriction
     * @param n           number of clients
     * @param stockMap    Map of all producers and their stock
     * @param clientsMap  a map with the clients and the products they want to buy.
     * @return A list of Cabaz objects.
     */
    public List<Cabaz> expedatingForDay(int day, int restriction, int n, Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockMap,
                                        Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> clientsMap) {

        Map<Pessoa, Map<Pessoa, Integer>> hubProximoCliente = null;
        Map<Pessoa, Map<Produtor, Integer>> produtoresProximoHub = null;

        if (restriction == 1) {
            hubProximoCliente = expedatingStore.getHubProximoCliente();//O(n^3)
            produtoresProximoHub = expedatingStore.produtoresEachHub(1);//O(n^3)
        }

        List<Cabaz> expedatingList = new ArrayList<>();
        List<InfoProduto> infoProdutoList;
        InfoProduto infoProdutoAuxiliar;
        Produtos produtoCriarObjeto;
        Double valorExpectado;
        Pessoa clientInfo;

        for (Map.Entry<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> entryClient : clientsMap.entrySet()) {//O(n)
            infoProdutoList = new ArrayList<>();
            clientInfo = entryClient.getKey();

            Map<Produtos, Double> clientesCabazez = entryClient.getValue().get(day);
            for (Map.Entry<Produtos, Double> infoProdutoCabaz : clientesCabazez.entrySet()) {//O(n)
                produtoCriarObjeto = infoProdutoCabaz.getKey();
                valorExpectado = infoProdutoCabaz.getValue();

                if (valorExpectado > 0) {
                    if (restriction == 0) {
                        infoProdutoAuxiliar = findObtainedValueNoRestriction(day, produtoCriarObjeto, valorExpectado, stockMap);//O(n)
                    } else {
                        Pessoa auxiHub = hubProximoCliente.get(clientInfo).entrySet().iterator().next().getKey();
                        Map<Produtor, Integer> auxMap = produtoresProximoHub.get(auxiHub);
                        infoProdutoAuxiliar = findObtainedValueRestriction(day, produtoCriarObjeto, valorExpectado, stockMap, auxMap, clientInfo);//O(n)
                    }
                    if (infoProdutoAuxiliar != null) {
                        infoProdutoList.add(infoProdutoAuxiliar);
                    } else {
                        infoProdutoList.add(new InfoProduto(produtoCriarObjeto.getNomeFruta(), valorExpectado, 0.0, "Produtor Inexistente"));
                    }
                }
            }
            if (infoProdutoList.size() > 0) {
                expedatingList.add(new Cabaz(clientInfo.getIdPessoa(), day, infoProdutoList));
            }
        }
        expedatingStore.setExpedatingList(expedatingList);

        return expedatingList;
    }//O(n^3)

    //US309


    /**
     * It finds the producer with the most quantity of a product, and returns the information about it
     *
     * @param day            the day of the week
     * @param produtoFind    The product we're looking for
     * @param needed         the quantity of the product that the client needs
     * @param stockMap       Map of producers and their stock.
     * @param mapRestriction Map<Produtor, Integer>
     * @param clientInfo     The client that is buying the product
     * @return The method returns a InfoProduto object.
     */
    private InfoProduto findObtainedValueRestriction(int day, Produtos produtoFind, Double needed, Map<Produtor,
            TreeMap<Integer, Map<Produtos, Double>>> stockMap, Map<Produtor, Integer> mapRestriction, Pessoa clientInfo) {
        Produtor produtorDoFor = null;
        double finalQuantity = 0.0;
        double maxQuantity = 0.0;
        Produtor finalProducerr = null;
        int finalDay = 0;

        for (Map.Entry<Produtor, TreeMap<Integer, Map<Produtos, Double>>> producer : stockMap.entrySet()) {//O(n)

            if (mapRestriction.get(producer.getKey()) != null) {

                double quantityBeforeYesterday = getQuantityOrDefault(producer, day - 2, produtoFind, 0.0);
                double quantityYesterday = getQuantityOrDefault(producer, day - 1, produtoFind, 0.0);
                double quantityToday = getQuantityOrDefault(producer, day, produtoFind, 0.0);
                double totalQuantity = quantityBeforeYesterday + quantityYesterday + quantityToday;

                if (quantityBeforeYesterday > maxQuantity) {
                    maxQuantity = quantityBeforeYesterday;
                    finalProducerr = producer.getKey();
                    finalDay = day - 2;
                } else if (quantityYesterday > maxQuantity) {
                    maxQuantity = quantityYesterday;
                    finalProducerr = producer.getKey();
                    finalDay = day - 1;
                } else if (quantityToday > maxQuantity) {
                    maxQuantity = quantityToday;
                    finalProducerr = producer.getKey();
                    finalDay = day;
                }

                if (totalQuantity >= needed) {
                    produtorDoFor = producer.getKey();
                    finalQuantity = needed;
                    updateStockQuantities(producer, day - 2, day, produtoFind, totalQuantity - needed);
                    break;
                }
            }
        }

        if (produtorDoFor != null) {
            return new InfoProduto(produtoFind.getNomeFruta(), needed, finalQuantity, produtorDoFor.getId());
        }

        if (finalProducerr != null) {
            stockMap.get(finalProducerr).get(finalDay).put(produtoFind, 0.0);
            return new InfoProduto(produtoFind.getNomeFruta(), needed, maxQuantity, finalProducerr.getId());//O(logn)
        }
        return null;
    }

    //US308


    /**
     * It iterates through the map of producers and their stock, and finds the producer with the most stock of the product
     * we're looking for
     *
     * @param day         the day of the week
     * @param produtoFind The product we're looking for
     * @param needed      the quantity of the product that is needed
     * @param stockMap    a map that contains the stock of each producer.
     * @return The method returns an object of type InfoProduto.
     */
    private InfoProduto findObtainedValueNoRestriction(int day, Produtos produtoFind, Double needed, Map<Produtor,
            TreeMap<Integer, Map<Produtos, Double>>> stockMap) {
        Produtor finalProducer = null;
        double finalQuantity = 0.0;
        double maxQuantity = 0.0;
        Produtor finalProducerr = null;
        int finalDay = 0;

        for (Map.Entry<Produtor, TreeMap<Integer, Map<Produtos, Double>>> producer : stockMap.entrySet()) {//O(n)
            double quantityBeforeYesterday = getQuantityOrDefault(producer, day - 2, produtoFind, 0.0);
            double quantityYesterday = getQuantityOrDefault(producer, day - 1, produtoFind, 0.0);
            double quantityToday = getQuantityOrDefault(producer, day, produtoFind, 0.0);
            double totalQuantity = quantityBeforeYesterday + quantityYesterday + quantityToday;

            if (quantityBeforeYesterday > maxQuantity) {
                maxQuantity = quantityBeforeYesterday;
                finalProducerr = producer.getKey();
                finalDay = day - 2;
            } else if (quantityYesterday > maxQuantity) {
                maxQuantity = quantityYesterday;
                finalProducerr = producer.getKey();
                finalDay = day - 1;
            } else if (quantityToday > maxQuantity) {
                maxQuantity = quantityToday;
                finalProducerr = producer.getKey();
                finalDay = day;
            }

            if (totalQuantity >= needed) {
                finalProducer = producer.getKey();
                finalQuantity = needed;
                updateStockQuantities(producer, day - 2, day, produtoFind, totalQuantity - needed);
                break;
            }
        }

        if (finalProducer != null) {
            return new InfoProduto(produtoFind.getNomeFruta(), needed, finalQuantity, finalProducer.getId());
        }

        if (finalProducerr != null) {
            stockMap.get(finalProducerr).get(finalDay).put(produtoFind, 0.0);//O(logn)
            return new InfoProduto(produtoFind.getNomeFruta(), needed, maxQuantity, finalProducerr.getId());
        }
        return null;
    }//O(n)

    //US308/309


    /**
     * It iterates through the days between the start and end day, and subtracts the quantityToSubtract from the quantity
     * of the product in the producer's stock
     *
     * @param producer           the producer we're currently looking at
     * @param startDay           the day the client wants to buy the product
     * @param endDay             the day the client wants to buy the product
     * @param produtoFind        The product we're looking for
     * @param quantityToSubtract The quantity of the product that we want to subtract from the stock.
     */
    private void updateStockQuantities(Map.Entry<Produtor, TreeMap<Integer, Map<Produtos, Double>>> producer,
                                       int startDay, int endDay, Produtos produtoFind, double quantityToSubtract) {
        for (int x = startDay; x <= endDay; x++) {//O(k)
            if (producer.getValue().get(x) != null) {
                if (producer.getValue().get(x).get(produtoFind) != null) {
                    double currentQuantity = producer.getValue().get(x).get(produtoFind);
                    double updatedQuantity = Math.max(currentQuantity - quantityToSubtract, 0);
                    producer.getValue().get(x).put(produtoFind, updatedQuantity);
                    quantityToSubtract -= currentQuantity;
                }
            }
        }
    }//O(1)


    /**
     * If the map contains the key, return the value, otherwise return the default value.
     *
     * @param producer     the producer
     * @param day          the day of the week
     * @param produtoFind  The product we're looking for
     * @param defaultValue The value to return if the value is not found.
     * @return The quantity of a product produced by a producer in a given day.
     */
    private double getQuantityOrDefault(Map.Entry<Produtor, TreeMap<Integer, Map<Produtos, Double>>> producer,
                                        int day, Produtos produtoFind, double defaultValue) {
        return Optional.ofNullable(producer.getValue().get(day))
                .map(m -> m.get(produtoFind))
                .orElse(defaultValue);
    }//O(1)


    //US310


    /**
     * It calculates the minimum distance between all the producers and the hubs, and then it calculates the minimum
     * distance between all the hubs and the clients
     *
     * @param auxList    List of all the baskets of the day
     * @param clientsMap a map with the clients of the day as keys and a map with the products they bought as values.
     * @param day        the day of the week
     * @return The return is a map with the path and the distance.
     */
    public Map<List<Pessoa>, Integer> expedatingPath(List<Cabaz> auxList, Map<Pessoa, TreeMap<Integer, Map<Produtos, Double>>> clientsMap, int day) {
        Set<Pessoa> auxiliarClientList = clientsMap.keySet(); //Clientes do Dia
        Map<Pessoa, Map<Pessoa, Integer>> hubEachClient = expedatingStore.getHubProximoCliente();

        LinkedList<Pessoa> pessoaLinkedList = new LinkedList<>(); //em forma de objeto os clientes
        //O(n^2)
        for (Cabaz auxiliarPessoa : auxList) {
            for (Pessoa auxiliarClient : auxiliarClientList) {
                if (auxiliarClient.getIdPessoa().equals(auxiliarPessoa.getCliente_Id())) {
                    pessoaLinkedList.add(auxiliarClient);
                }
            }
        }

        LinkedList<Pessoa> hubLinkedList = new LinkedList<>();
        //O(n^2)
        for (Pessoa auxiliarPessoa : pessoaLinkedList) {
            Pessoa hub = hubEachClient.get(auxiliarPessoa).entrySet().iterator().next().getKey();
            if (!hubLinkedList.contains(hub)) {
                hubLinkedList.add(hub);
            }
        }

        LinkedList<String> produtores = new LinkedList<>();
        //0(n^2)
        for (Cabaz auxCabaz : auxList) {
            for (InfoProduto infoProduto : auxCabaz.getList()) {
                if (!produtores.contains(infoProduto.getNomeProdutor())) {
                    produtores.add(infoProduto.getNomeProdutor());
                }
            }
        }

        List<Produtor> produtoresList = findMethods.findAllProducers();
        LinkedList<Pessoa> produtoresObjeto = new LinkedList<>();
        //O(n^2)
        for (String aux : produtores) {
            for (Produtor produtor : produtoresList) {
                if (aux.equals(produtor.getId())) {
                    produtoresObjeto.add(produtor);
                    break;
                }
            }
        }

        LinkedList<Pessoa> total = new LinkedList<>(hubLinkedList);
        total.addAll(produtoresObjeto);

        MapGraph<Pessoa, Integer> graph = mapGraphPessoaStore.getMapGraph();
        MatrixGraph<Pessoa, Integer> matrixGraph = Algorithms.minDistGraph(graph, comparator, binaryOperator);// O(n^3)

        boolean[] visited;
        int numProducers = produtoresObjeto.size();
        int sum, minDist = 0, sumMinDist = -1;
        List<Pessoa> minPath = new ArrayList<>();
        List<Pessoa> path;
        for (Pessoa pessoa : total) {// O(n)
            if (pessoa instanceof Produtor) {
                path = new ArrayList<>();
                visited = new boolean[total.size()];
                Pessoa vOrig = pessoa;
                path.add(vOrig);
                Pessoa vOrigAux = null;
                int counter = 1;
                sum = 0;
                while (counter < total.size()) {//O(n)
                    minDist = -1;
                    int vOrigKey = total.indexOf(vOrig);
                    visited[vOrigKey] = true;
                    for (Edge<Pessoa, Integer> edge : matrixGraph.outgoingEdges(vOrig)) {//O(n)
                        if (counter < numProducers) {
                            Pessoa vDest = edge.getVDest();
                            if (vDest instanceof Produtor) {
                                int vDestKey = total.indexOf(edge.getVDest());
                                if (vDestKey != -1) {
                                    if (!visited[vDestKey] && total.contains(vDest)) {
                                        if (edge.getWeight() < minDist || minDist < 0) {
                                            minDist = edge.getWeight();
                                            vOrigAux = vDest;
                                        }
                                    }
                                }
                            }
                        } else if (counter >= numProducers) {
                            Pessoa vDest = edge.getVDest();
                            int vDestKey = total.indexOf(edge.getVDest());
                            if (vDestKey != -1) {
                                if (!visited[vDestKey] && total.contains(vDest)) {
                                    if (edge.getWeight() < minDist || minDist < 0) {
                                        minDist = edge.getWeight();
                                        vOrigAux = vDest;
                                    }
                                }
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
        }
        Map<List<Pessoa>, Integer> returnableMap = new HashMap<>();
        returnableMap.put(minPath, sumMinDist);

        return returnableMap;
    }//O(n^3)

}
