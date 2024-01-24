package Methods;

import Company.Model.*;
import Company.Store.ExpedatingStore;
import Company.Store.MapStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Estatistics.
 */
public class Statistics {

    private FindMethods findMethods;
    private ExpedatingStore expedatingStore;
    private Expedating expedating;
    private MapStore mapStore;

    // Creating a constructor for the class Statistics.
    public Statistics(FindMethods findMethods, ExpedatingStore expedatingStore, Expedating expedating, MapStore mapStore) {
        this.findMethods = findMethods;
        this.expedating = expedating;
        this.expedatingStore = expedatingStore;
        this.mapStore = mapStore;
    }


    /**
     * It takes a list of cabaz objects, and returns a list of cabaz objects, a list of produtor objects, a list of hub
     * objects and an expedition object
     */
    public void printMainStatistics() {
        List<StatisticsByCabaz> statisticsbyCabazList = cabazStatistics();//O(n^2)
        ExpeditionStatistics expeditionStatisticsObject = expeditionStatistics(statisticsbyCabazList);
        List<StatisticsByProdutor> statisticsByProdutorList = produtorStatistics(statisticsbyCabazList, expeditionStatisticsObject);
        List<StatisticsByHub> statisticsByHubList = hubStatistics(statisticsbyCabazList, statisticsByProdutorList);

        for (StatisticsByCabaz cabazprint : statisticsbyCabazList) {//O(n)
            System.out.println(cabazprint);
            System.out.println();

        }
        for (StatisticsByProdutor produtorprint : statisticsByProdutorList) {//O(n)
            System.out.println(produtorprint);
            System.out.println();
        }
        for (StatisticsByHub hubprint : statisticsByHubList) {//O(n)
            System.out.println(hubprint);
            System.out.println();
        }
        System.out.println(expeditionStatisticsObject);//O(1)

    }


    /**
     * It iterates through a list of cabaz objects, and for each cabaz object it iterates through a list of infoProduto
     * objects, and for each infoProduto object it iterates through a list of strings
     *
     * @return A list of StatisticsByCabaz objects.
     */
    public List<StatisticsByCabaz> cabazStatistics() {
        List<Cabaz> cabazList = expedatingStore.getExpedatingList();
        int unsatisfiedProduct, parcialSatisfiedProduct, satisfiedProduct;
        List<String> producersByCabaz;
        List<StatisticsByCabaz> statisticsByCabazList = new ArrayList<>();
        for (Cabaz cabaz : cabazList) {//O(n)
            parcialSatisfiedProduct = 0;
            satisfiedProduct = 0;
            unsatisfiedProduct = 0;
            producersByCabaz = new ArrayList<>();
            for (InfoProduto infoProduto : cabaz.getList()) {//O(n*n)
                if (infoProduto.getEstado() == 0) {
                    unsatisfiedProduct++;
                } else if (infoProduto.getEstado() == 1) {
                    parcialSatisfiedProduct++;
                } else if (infoProduto.getEstado() == 2) {
                    satisfiedProduct++;
                }
                if (!producersByCabaz.contains(infoProduto.getNomeProdutor())) {
                    producersByCabaz.add(infoProduto.getNomeProdutor());
                }
            }
            statisticsByCabazList.add(new StatisticsByCabaz(satisfiedProduct, parcialSatisfiedProduct, unsatisfiedProduct,
                    producersByCabaz.size(), producersByCabaz, cabaz.getCliente_Id(), cabaz.getDia()));
        }
        return statisticsByCabazList;
    }//O(n^2)


    /**
     * It iterates through a list of StatisticsByCabaz objects, and for each one it checks if the cabaz is satisfied,
     * partially satisfied or not satisfied, and adds the producers to a list of producers that are not repeated
     *
     * @param statisticsByCabazList List of StatisticsByCabaz objects
     * @return A list of expedition statistics.
     */
    public ExpeditionStatistics expeditionStatistics(List<StatisticsByCabaz> statisticsByCabazList) {
        int cabazSatisfeitos = 0;
        int cabazParcialmenteSatisfeito = 0;
        int cabazNaoSatisfeito = 0;
        List<String> produtoresNaoRepetidos = new ArrayList<>();

        for (StatisticsByCabaz statisticsByCabaz : statisticsByCabazList) {//O(n)
            if (statisticsByCabaz.getPercentagemCabazSatisfeito() == 100) {
                cabazSatisfeitos++;
            } else if (statisticsByCabaz.getPercentagemCabazSatisfeito() > 0 && statisticsByCabaz.getPercentagemCabazSatisfeito() < 100) {
                cabazParcialmenteSatisfeito++;
            } else {
                cabazNaoSatisfeito++;
            }
            for (String produtor : statisticsByCabaz.getProducersByCabaz()) {//O(n)
                if (!produtoresNaoRepetidos.contains(produtor)) {
                    produtoresNaoRepetidos.add(produtor);
                }
            }
        }

        return new ExpeditionStatistics(statisticsByCabazList.get(0).getDia(), cabazSatisfeitos, cabazParcialmenteSatisfeito,
                cabazNaoSatisfeito, produtoresNaoRepetidos.size(), produtoresNaoRepetidos);
    }//O(n^2)


    /**
     * This function takes a list of StatisticsByCabaz objects and an ExpeditionStatistics object as parameters and returns
     * a list of StatisticsByProdutor objects
     *
     * @param statisticsbyCabazList      List of StatisticsByCabaz objects
     * @param expeditionStatisticsObject Object that contains the list of producers and clients that were used in the
     *                                   simulation.
     * @return A list of statistics by producer.
     */
    public List<StatisticsByProdutor> produtorStatistics(List<StatisticsByCabaz> statisticsbyCabazList, ExpeditionStatistics expeditionStatisticsObject) {
        List<StatisticsByProdutor> statisticsByProdutorList = new ArrayList<>();
        List<String> produtores = expeditionStatisticsObject.getProdutoresNaoRepetidos();
        List<String> clientes;
        int cabazTotalmenteFornecido, cabazParcialmenteFornecidos, esgotadoProdutos;
        List<Pessoa> listClient = findMethods.findAllClients();//O(n)
        List<Produtor> listProducer = findMethods.findAllProducers();//O(n)
        Map<Pessoa, Map<Pessoa, Integer>> hubClient = expedatingStore.getHubProximoCliente();//O(n^3)
        Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockMap = mapStore.getstockProdutorMap();
        List<Produtor> produtorListObject = findProdutorObjectList(listProducer, produtores);
        int dia = 0;
        for (Produtor produtor : produtorListObject) {//O(n)
            cabazTotalmenteFornecido = 0;
            cabazParcialmenteFornecidos = 0;
            clientes = new ArrayList<>();

            for (StatisticsByCabaz cabaz : statisticsbyCabazList) {//O(n)
                dia = cabaz.getDia();
                if (cabaz.getProducersByCabaz().contains(produtor.getIdPessoa()) && cabaz.getProducersByCabaz().contains("Produtor Inexistente") && cabaz.getPercentagemCabazSatisfeito() == 100 && cabaz.getProducersByCabaz().size() == 2 || cabaz.getProducersByCabaz().contains(produtor.getIdPessoa()) && cabaz.getProducersByCabaz().size() == 1 && cabaz.getPercentagemCabazSatisfeito() == 100) {
                    cabazTotalmenteFornecido++;
                    if (!clientes.contains(cabaz.getCliente())) {
                        clientes.add(cabaz.getCliente());
                    }
                } else if (cabaz.getProducersByCabaz().contains(produtor.getIdPessoa()) && cabaz.getProducersByCabaz().size() == 1 || cabaz.getProducersByCabaz().contains(produtor.getIdPessoa()) && cabaz.getProducersByCabaz().size() > 1) {
                    cabazParcialmenteFornecidos++;
                    if (!clientes.contains(cabaz.getCliente())) {
                        clientes.add(cabaz.getCliente());
                    }
                }
            }
            List<Pessoa> listClientObject = findClientObjectList(listClient, clientes);//O(n^2)
            esgotadoProdutos = produtosEsgotados(produtor, stockMap, dia);//O(n)
            List<Empresa> hubsFornecidos = hubsExistentes(hubClient, listClientObject);//O(n)

            statisticsByProdutorList.add(new StatisticsByProdutor(produtor, cabazTotalmenteFornecido, cabazParcialmenteFornecidos,
                    esgotadoProdutos, hubsFornecidos));

        }

        return statisticsByProdutorList;

    }//O(n^3)


    /**
     * It iterates through a list of clients, and for each client, it gets the first hub in the map of hubs and clients,
     * and if that hub is not already in the list of hubs, it adds it to the list of hubs
     *
     * @param hubClient  a map that contains the clients and the hubs that they are connected to.
     * @param listClient List of clients
     * @return A list of hubs.
     */
    private List<Empresa> hubsExistentes(Map<Pessoa, Map<Pessoa, Integer>> hubClient, List<Pessoa> listClient) {
        List<Empresa> hubs = new ArrayList<>();
        for (Pessoa cliente : listClient) {//O(n)
            Empresa aux = (Empresa) hubClient.get(cliente).entrySet().iterator().next().getKey();
            if (!hubs.contains(aux)) {
                hubs.add(aux);
            }
        }
        return hubs;
    }
//O(n)


    /**
     * "Count the number of products that are out of stock for a given producer on a given day."
     * <p>
     * The function is called with a producer, a map of producers to a map of days to a map of products to stock, and a
     * day. It returns the number of products that are out of stock for the given producer on the given day
     *
     * @param produtor the producer
     * @param stockMap a map with the following structure:
     * @param dia      the day of the week
     * @return The number of products that are out of stock.
     */
    private int produtosEsgotados(Produtor produtor, Map<Produtor, TreeMap<Integer, Map<Produtos, Double>>> stockMap, int dia) {
        Map<Produtos, Double> stockDia = stockMap.get(produtor).get(dia);
        int esgotados = 0;
        for (Map.Entry<Produtos, Double> prr : stockDia.entrySet()) {//O(n)
            if (prr.getValue() == 0) {
                esgotados++;
            }
        }
        return esgotados;
    }//O(n)


    /**
     * It takes a list of Produtor objects and a list of Strings, and returns a list of Produtor objects
     *
     * @param listProducer List of all producers
     * @param produtores   List of producers' names
     * @return A list of producers that exist in the database.
     */
    private List<Produtor> findProdutorObjectList(List<Produtor> listProducer, List<String> produtores) {
        List<Produtor> produtoresExistentes = new ArrayList<>();

        for (String produtorNome : produtores) {//O(n)

            for (Produtor produtor : listProducer) {//O(n)
                if (produtor.getIdPessoa().equals(produtorNome)) {
                    produtoresExistentes.add(produtor);
                    break;
                }
            }
        }
        return produtoresExistentes;
    }//O(n^2)


    /**
     * It takes a list of clients and a list of client names and returns a list of clients that exist in the list of
     * clients
     *
     * @param listClient List of all clients
     * @param clientes   List of clients that are in the database
     * @return A list of clients that exist in the database.
     */
    private List<Pessoa> findClientObjectList(List<Pessoa> listClient, List<String> clientes) {
        List<Pessoa> clientesExistentes = new ArrayList<>();
        for (String clientNome : clientes) {//O(n)
            for (Pessoa client : listClient) {//O(n)
                if (client.getIdPessoa().equals(clientNome)) {
                    clientesExistentes.add(client);
                    break;
                }
            }
        }
        return clientesExistentes;
    }//O(n^2)


    /**
     * This function is used to get the number of clients that are using a specific hub
     *
     * @param statisticsbyCabazList    List of StatisticsByCabaz objects
     * @param statisticsByProdutorList List of StatisticsByProdutor objects
     * @return A list of StatisticsByHub objects.
     */
    public List<StatisticsByHub> hubStatistics(List<StatisticsByCabaz> statisticsbyCabazList, List<StatisticsByProdutor> statisticsByProdutorList) {
        List<StatisticsByHub> statisticsByHubList = new ArrayList<>();
        List<Pessoa> clientsUsed = new ArrayList<>();
        List<Pessoa> producersUsed = new ArrayList<>();
        List<Pessoa> listClient = findMethods.findAllClients();//O(n)
        List<Empresa> listHubs = findMethods.findAllHubs();//O(n)
        List<String> listClientName = new ArrayList<>();
        Map<Pessoa, Map<Pessoa, Integer>> hub = expedatingStore.getHubProximoCliente();//O(1)

        for (StatisticsByCabaz cabaz : statisticsbyCabazList) {//O(n)
            if (!listClientName.contains(cabaz.getCliente())) {
                listClientName.add(cabaz.getCliente());
            }
        }

        for (String name : listClientName) {//O(n)
            for (Pessoa pessoa : listClient) {//O(n)
                if (pessoa.getIdPessoa().equals(name)) {
                    clientsUsed.add(pessoa);
                    break;
                }
            }
        }

        int clientes, produtores;
        for (Pessoa hubss : listHubs) {//O(n)
            clientes = 0;
            produtores = 0;
            for (Pessoa pessoaAuxiliar : clientsUsed) {//O(n)
                if (hub.get(pessoaAuxiliar).entrySet().iterator().next().getKey().equals(hubss)) {
                    clientes++;
                }
            }
            for (StatisticsByProdutor produtor : statisticsByProdutorList) {
                for (Empresa emp : produtor.getHubsFornecidos()) {
                    if (hubss.equals(emp)) {
                        produtores++;
                    }
                }
            }

            statisticsByHubList.add(new StatisticsByHub(hubss, clientes, produtores));
        }

        return statisticsByHubList;
    }//O(n^2)
}
