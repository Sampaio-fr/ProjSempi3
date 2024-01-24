package Methods;

import Company.Controller.MainController;
import Company.Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Expedating test.
 */
public class ExpedatingTest {
    private MainController mainController;


    /**
     * Initialize tests.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    public void initializeTests() throws Exception {
        mainController = new MainController();
        URL clientsUrl = Thread.currentThread().getContextClassLoader().getResource("clientes-produtores_small.csv");
        URL distancesUrl = Thread.currentThread().getContextClassLoader().getResource("distancias_small.csv");
        mainController.lerClienteProdutor(clientsUrl.getPath());
        mainController.lerDistancias(distancesUrl.getPath());
        mainController.setMapGraph(mainController.createGraph());


    }

    /**
     * Test expedating for day no restriction.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpedatingForDayNoRestriction() throws Exception {
        int day = 1;
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        List<Cabaz> expected = new ArrayList<>();

        List<InfoProduto> infoProdutos = new ArrayList<>();
        InfoProduto info1 = new InfoProduto("Prod5", 5.0, 5.0, "P1");
        InfoProduto info2 = new InfoProduto("Prod6", 2.0, 2.0, "P1");
        InfoProduto info3 = new InfoProduto("Prod11", 2.5, 2.5, "P1");
        infoProdutos.add(info1);
        infoProdutos.add(info2);
        infoProdutos.add(info3);
        Cabaz c1 = new Cabaz("C1", day, infoProdutos);
        expected.add(c1);

        List<Cabaz> result = mainController.expedatingForDayNoRestrictions(day);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedating for day no restriction same cabaz.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpedatingForDayNoRestrictionSameCabaz() throws Exception {
        int day = 1;
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_SameCabazMultipleTimes.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        List<Cabaz> expected = new ArrayList<>();

        List<InfoProduto> infoProdutos = new ArrayList<>();
        InfoProduto info1 = new InfoProduto("Prod5", 5.0, 5.0, "P1");
        InfoProduto info2 = new InfoProduto("Prod6", 2.0, 2.0, "P1");
        InfoProduto info3 = new InfoProduto("Prod11", 2.5, 2.5, "P1");
        infoProdutos.add(info1);
        infoProdutos.add(info2);
        infoProdutos.add(info3);
        Cabaz c1 = new Cabaz("C1", day, infoProdutos);
        expected.add(c1);

        List<Cabaz> result = mainController.expedatingForDayNoRestrictions(day);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedating for day with restriction.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpedatingForDayWithRestriction() throws Exception {
        int day = 3;

        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_Restriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        List<Cabaz> expected = new ArrayList<>();

        List<InfoProduto> infoProdutos = new ArrayList<>();
        InfoProduto info1 = new InfoProduto("Prod5", 10.0, 10.0, "P1");
        InfoProduto info2 = new InfoProduto("Prod6", 4.0, 4.0, "P1");
        InfoProduto info3 = new InfoProduto("Prod11", 5.0, 5.0, "P1");
        infoProdutos.add(info1);
        infoProdutos.add(info2);
        infoProdutos.add(info3);
        Cabaz c1 = new Cabaz("C1", day, infoProdutos);
        expected.add(c1);


        List<Cabaz> result = mainController.expedatingForDayRestrictions(day, 1);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedating for day no restriction incomplete cabaz.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpedatingForDayNoRestrictionIncompleteCabaz() throws Exception {
        int day = 1;
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestrictionIncomplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        List<Cabaz> expected = new ArrayList<>();

        List<InfoProduto> infoProdutos = new ArrayList<>();
        InfoProduto info1 = new InfoProduto("Prod5", 5.0, 5.0, "P1");
        InfoProduto info2 = new InfoProduto("Prod6", 2.0, 2.0, "P1");
        InfoProduto info3 = new InfoProduto("Prod11", 2.5, 0.5, "P1");
        infoProdutos.add(info1);
        infoProdutos.add(info2);
        infoProdutos.add(info3);
        Cabaz c1 = new Cabaz("C1", day, infoProdutos);
        expected.add(c1);

        List<Cabaz> result = mainController.expedatingForDayNoRestrictions(day);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedating for day with restriction incomplete cabaz.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpedatingForDayWithRestrictionIncompleteCabaz() throws Exception {
        int day = 3;

        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_RestrictionIncomplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        List<Cabaz> expected = new ArrayList<>();

        List<InfoProduto> infoProdutos = new ArrayList<>();
        InfoProduto info1 = new InfoProduto("Prod5", 10.0, 10.0, "P1");
        InfoProduto info2 = new InfoProduto("Prod6", 4.0, 4.0, "P1");
        InfoProduto info3 = new InfoProduto("Prod11", 5.0, 3.0, "P1");
        infoProdutos.add(info1);
        infoProdutos.add(info2);
        infoProdutos.add(info3);
        Cabaz c1 = new Cabaz("C1", day, infoProdutos);
        expected.add(c1);


        List<Cabaz> result = mainController.expedatingForDayRestrictions(day, 1);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expediting path.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpeditingPath() throws Exception {
        int day = 1;
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();

        mainController.expedatingForDayNoRestrictions(day);
        Map<List<Pessoa>, Integer> map = mainController.pathWay(day);
        Map.Entry<List<Pessoa>, Integer> entry = map.entrySet().iterator().next();

        int distanceResult = entry.getValue();
        int expectedDistance = 62879;

        System.out.println("Expected distance:");
        System.out.println(expectedDistance);
        System.out.println("Actual distance:");
        System.out.println(distanceResult);

        Assertions.assertEquals(expectedDistance, distanceResult, "Test passed!");

        List<Pessoa> resultList = entry.getKey();
        List<Pessoa> expectedList = new ArrayList<>();
        Produtor producer = new Produtor("CT17", "P1", new Point2D.Double(40.6667, -7.9167));
        Empresa hub = new Empresa("CT9", "E4", new Point2D.Double(40.5364, -7.2683));
        expectedList.add(producer);
        expectedList.add(hub);

        System.out.println("Expected path:");
        System.out.println(expectedList);
        System.out.println("Actual distance:");
        System.out.println(resultList);

        Assertions.assertEquals(expectedList.toString(), resultList.toString(), "Test passed!");
    }
}
