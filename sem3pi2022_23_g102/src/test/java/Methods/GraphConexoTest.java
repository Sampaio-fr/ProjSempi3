package Methods;

import Company.Controller.MainController;
import Company.Graph.map.MapGraph;
import Company.Model.Empresa;
import Company.Model.Helper;
import Company.Model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Graph conexo test.
 */
class GraphConexoTest {

    private MainController mainController;
    private MapGraph<Pessoa, Integer> graph;

    /**
     * Initialize exercises.
     */
    @BeforeEach
    public void initializeExercises() {
        mainController = new MainController();
        List<Pessoa> pessoaList = new ArrayList<>();

        Pessoa p1 = new Pessoa("CT1", new Point2D.Double(10, 20), "CT1");
        Pessoa p2 = new Pessoa("CT2", new Point2D.Double(20, 10), "CT2");
        Pessoa p3 = new Pessoa("CT3", new Point2D.Double(1, 2), "CT3");
        Empresa e1 = new Empresa("CT10", "e1", new Point2D.Double(1, 1));
        Empresa e2 = new Empresa("CT11", "e2", new Point2D.Double(2, 2));

        pessoaList.add(p1);
        pessoaList.add(p2);
        pessoaList.add(p3);
        pessoaList.add(e1);
        pessoaList.add(e2);

        mainController.setClientList(pessoaList);
    }

    /**
     * Verificar graph conexo true.
     */
    @Test
    void verificarGraphConexoTrue() {
        List<Helper> helperList = new ArrayList<>();

        Helper h1 = new Helper("CT1", "CT2", 1);
        Helper h2 = new Helper("CT1", "CT3", 2);
        Helper h3 = new Helper("CT2", "CT3", 5);
        Helper h4 = new Helper("CT10", "CT1", 10);
        Helper h5 = new Helper("CT10", "CT2", 10);
        Helper h6 = new Helper("CT10", "CT3", 10);
        Helper h7 = new Helper("CT11", "CT1", 20);
        Helper h8 = new Helper("CT11", "CT2", 20);
        Helper h9 = new Helper("CT11", "CT3", 20);

        helperList.add(h1);
        helperList.add(h2);
        helperList.add(h3);
        helperList.add(h4);
        helperList.add(h5);
        helperList.add(h6);
        helperList.add(h7);
        helperList.add(h8);
        helperList.add(h9);

        mainController.setHelperList(helperList);
        graph = mainController.createGraph();
        mainController.setMapGraph(graph);

        boolean res = mainController.verificarGraphConexo();

        Assertions.assertTrue(res);
    }

    /**
     * Verificar graph conexo false.
     */
    @Test
    void verificarGraphConexoFalse() {
        graph = mainController.createGraph();
        mainController.setMapGraph(graph);

        boolean res = mainController.verificarGraphConexo();

        Assertions.assertFalse(res);
    }

    /**
     * Numero ligacoes diam.
     */
    @Test
    void numeroLigacoesDiam() {
        Assertions.assertEquals(0, mainController.numeroligacoesDiam(), "No edges");

        List<Helper> helperList = new ArrayList<>();

        Helper h1 = new Helper("CT1", "CT2", 1);
        Helper h2 = new Helper("CT1", "CT3", 2);
        Helper h3 = new Helper("CT2", "CT3", 5);
        Helper h4 = new Helper("CT10", "CT1", 10);
        Helper h5 = new Helper("CT10", "CT2", 10);
        Helper h6 = new Helper("CT10", "CT3", 10);
        Helper h7 = new Helper("CT11", "CT1", 20);
        Helper h8 = new Helper("CT11", "CT2", 20);
        Helper h9 = new Helper("CT11", "CT3", 20);

        helperList.add(h1);
        helperList.add(h2);
        helperList.add(h3);
        helperList.add(h4);
        helperList.add(h5);
        helperList.add(h6);
        helperList.add(h7);
        helperList.add(h8);
        helperList.add(h9);

        mainController.setHelperList(helperList);
        graph = mainController.createGraph();
        mainController.setMapGraph(graph);

        Assertions.assertEquals(2, mainController.numeroligacoesDiam());
    }

    /**
     * Numero ligacoes diam null.
     */
    @Test
    void numeroLigacoesDiamNull() {
        graph = new MapGraph<>(false);
        mainController.setMapGraph(graph);

        Assertions.assertEquals(0, mainController.numeroligacoesDiam());
    }
}