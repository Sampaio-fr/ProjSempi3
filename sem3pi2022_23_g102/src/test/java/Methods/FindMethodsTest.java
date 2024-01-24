package Methods;

import Company.Controller.MainController;
import Company.Graph.map.MapGraph;
import Company.Model.Cliente;
import Company.Model.Empresa;
import Company.Model.Helper;
import Company.Model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * The type Find methods test.
 */
class FindMethodsTest {

    private MainController mainController;
    private MapGraph<Pessoa, Integer> graph;

    /**
     * Initialize exercises.
     */
    @BeforeEach
    public void initializeExercises() {
        mainController = new MainController();
        List<Pessoa> pessoaList = new ArrayList<>();
        List<Helper> helperList = new ArrayList<>();

        Pessoa p1 = new Cliente("CT1", "C1", new Point2D.Double(10, 20));
        Pessoa p2 = new Cliente("CT2", "C2", new Point2D.Double(20, 10));
        Pessoa p3 = new Cliente("CT3", "C3", new Point2D.Double(1, 2));
        Empresa e1 = new Empresa("CT10", "E1", new Point2D.Double(1, 1));
        Empresa e2 = new Empresa("CT11", "E2", new Point2D.Double(2, 2));

        pessoaList.add(p1);
        pessoaList.add(p2);
        pessoaList.add(p3);
        pessoaList.add(e1);
        pessoaList.add(e2);

        Helper h1 = new Helper("CT1", "CT2", 1);
        Helper h2 = new Helper("CT1", "CT3", 2);
        Helper h3 = new Helper("CT2", "CT3", 3);
        Helper h4 = new Helper("CT10", "CT1", 11);
        Helper h5 = new Helper("CT10", "CT2", 12);
        Helper h6 = new Helper("CT10", "CT3", 13);
        Helper h7 = new Helper("CT11", "CT1", 21);
        Helper h8 = new Helper("CT11", "CT2", 22);
        Helper h9 = new Helper("CT11", "CT3", 23);

        helperList.add(h1);
        helperList.add(h2);
        helperList.add(h3);
        helperList.add(h4);
        helperList.add(h5);
        helperList.add(h6);
        helperList.add(h7);
        helperList.add(h8);
        helperList.add(h9);

        mainController.setClientList(pessoaList);
        mainController.setHelperList(helperList);
        graph = mainController.createGraph();
        mainController.setMapGraph(graph);
    }

    /**
     * Find clients with minimum distance.
     */
    @Test
    void findClientsWithMinimumDistance() {
        Map<List<Pessoa>, Integer> mg = mainController.findNetworkWithMinimumDistance();
        Map.Entry<List<Pessoa>, Integer> entry = mg.entrySet().iterator().next();

        Assertions.assertEquals(5, entry.getKey().size());
        Assertions.assertEquals(38, entry.getValue());
        System.out.println(entry.getKey());
    }

    /**
     * Find clients with minimum distance null.
     */
    @Test
    void findClientsWithMinimumDistanceNull() {
        graph = new MapGraph<>(false);
        mainController.setMapGraph(graph);

        Map<List<Pessoa>, Integer> mg = mainController.findNetworkWithMinimumDistance();
        Assertions.assertNull(mg);
    }

    /**
     * Test nearest hub all clients.
     */
    @Test
    void testNearestHubAllClients() {
        List<Pessoa> list = mainController.findNearestHubToAllClients(1);

        Empresa e1 = new Empresa("CT10", "E1", new Point2D.Double(1, 1));

        Assertions.assertEquals(e1, list.get(0));
    }

    /**
     * Test nearest hub all clients empty.
     */
    @Test
    void testNearestHubAllClientsEmpty() {
        graph = new MapGraph<>(false);
        mainController.setMapGraph(graph);

        List<Pessoa> actualList = mainController.findNearestHubToAllClients(1);
        List<Pessoa> expectedList = new ArrayList<>();

        Assertions.assertEquals(expectedList, actualList);
    }

    /**
     * Test closer hub each client.
     */
    @Test
    void testCloserHubEachClient() {
        Map<Pessoa, Map<Pessoa, Integer>> map = mainController.findNearestsHubEachClients(1);
        Iterator<Map.Entry<Pessoa, Map<Pessoa, Integer>>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Pessoa, Map<Pessoa, Integer>> entry = iterator.next();
            if (entry.getKey().equals("C1")) {
                Assertions.assertEquals("E1", entry.getValue().keySet().iterator().next());
            } else if (entry.getKey().equals("C2")) {
                Assertions.assertEquals("E1", entry.getValue().keySet().iterator().next());
            } else if (entry.getKey().equals("C3")) {
                Assertions.assertEquals("E1", entry.getValue().keySet().iterator().next());
            } else if (entry.getKey().equals("E1")) {
                Assertions.assertEquals("E2", entry.getValue().keySet().iterator().next());
            } else if (entry.getKey().equals("E2")) {
                Assertions.assertEquals("E1", entry.getValue().keySet().iterator().next());
            }
        }
    }


    /**
     * Test closer hub each client empty.
     */
    @Test
    void testCloserHubEachClientEmpty() {
        graph = new MapGraph<>(false);
        mainController.setMapGraph(graph);

        Map<Pessoa, Map<Pessoa, Integer>> actualMap = mainController.findNearestsHubEachClients(1);
        Map<Pessoa, Map<Pessoa, Integer>> expectedMap = new HashMap<>();

        Assertions.assertEquals(expectedMap, actualMap);
    }
}
