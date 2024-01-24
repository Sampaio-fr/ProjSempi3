package Methods;

import Company.Controller.MainController;
import Company.Graph.map.MapGraph;
import Company.Model.Helper;
import Company.Model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Create graph test.
 */
class CreateGraphTest {

    private MainController mainController;

    /**
     * Initialize exercises.
     */
    @BeforeEach
    public void initializeExercises() {
        mainController = new MainController();
    }

    /**
     * Test create graph.
     */
    @Test
    void testCreateGraph() {
        List<Pessoa> pessoaList = new ArrayList<>();
        List<Helper> helperList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");
        Pessoa p2 = new Pessoa("CT2", new Point2D.Double(20, 10), "CT2");
        Pessoa p3 = new Pessoa("CT3", new Point2D.Double(1, 2), "CT3");

        pessoaList.add(p1);
        pessoaList.add(p2);
        pessoaList.add(p3);

        Helper h1 = new Helper("CT1", "CT2", 10);
        Helper h2 = new Helper("CT1", "CT3", 20);
        Helper h3 = new Helper("CT2", "CT3", 1);

        helperList.add(h1);
        helperList.add(h2);
        helperList.add(h3);

        mainController.setClientList(pessoaList);
        mainController.setHelperList(helperList);
        MapGraph<Pessoa, Integer> graph = mainController.createGraph();

        Assertions.assertEquals(3, graph.numVertices());
    }

    /**
     * Test create graph same person.
     */
    @Test
    void testCreateGraphSamePerson() {
        List<Pessoa> pessoaList = new ArrayList<>();
        List<Helper> helperList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");
        Pessoa p2 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");
        Pessoa p3 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");

        pessoaList.add(p1);
        pessoaList.add(p2);
        pessoaList.add(p3);

        mainController.setClientList(pessoaList);
        mainController.setHelperList(helperList);
        MapGraph<Pessoa, Integer> graph = mainController.createGraph();

        Assertions.assertEquals(1, graph.numVertices());
    }

    /**
     * Verify distances.
     */
    @Test
    void verifyDistances() {
        List<Pessoa> pessoaList = new ArrayList<>();
        List<Helper> helperList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");
        Pessoa p2 = new Pessoa("CT2", new Point2D.Double(20, 10), "CT2");
        Pessoa p3 = new Pessoa("CT3", new Point2D.Double(1, 2), "CT3");

        pessoaList.add(p1);
        pessoaList.add(p2);
        pessoaList.add(p3);

        Helper h1 = new Helper("CT1", "CT2", 10);
        Helper h2 = new Helper("CT1", "CT3", 20);
        Helper h3 = new Helper("CT2", "CT3", 1);

        helperList.add(h1);
        helperList.add(h2);
        helperList.add(h3);

        mainController.setClientList(pessoaList);
        mainController.setHelperList(helperList);
        MapGraph<Pessoa, Integer> graph = mainController.createGraph();

        Assertions.assertEquals(10, graph.edge(p1, p2).getWeight());
        Assertions.assertEquals(20, graph.edge(p1, p3).getWeight());
        Assertions.assertEquals(1, graph.edge(p2, p3).getWeight());

    }

    /**
     * Test find pessoa by local doesnt exist.
     */
    @Test
    void testFindPessoaByLocalDoesntExist() {
        List<Pessoa> pessoaList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");

        pessoaList.add(p1);
        mainController.setClientList(pessoaList);

        Pessoa p2 = mainController.findPessoaByLocal("CT2");

        Assertions.assertNotEquals(p2, p1);
    }

    /**
     * Test find pessoa by local exists.
     */
    @Test
    void testFindPessoaByLocalExists() {
        List<Pessoa> pessoaList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");

        pessoaList.add(p1);
        mainController.setClientList(pessoaList);

        Pessoa p2 = mainController.findPessoaByLocal("CT1");

        Assertions.assertEquals(p2, p1);

    }
}