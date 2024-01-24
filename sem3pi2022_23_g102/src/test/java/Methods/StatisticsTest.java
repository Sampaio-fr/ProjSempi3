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

/**
 * The type Statistics test.
 */
public class StatisticsTest {
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
     * Test cabaz statistics complete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCabazStatisticsComplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByCabaz> result = mainController.getCabazStatistics();
        List<String> producersCabaz = new ArrayList<>();
        producersCabaz.add("P1");
        StatisticsByCabaz statistics = new StatisticsByCabaz(3, 0, 0, 1, producersCabaz, "C1", 1);
        List<StatisticsByCabaz> expected = new ArrayList<>();
        expected.add(statistics);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test cabaz statistics incomplete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCabazStatisticsIncomplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestrictionIncomplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByCabaz> result = mainController.getCabazStatistics();
        List<String> producersCabaz = new ArrayList<>();
        producersCabaz.add("P1");
        StatisticsByCabaz statistics = new StatisticsByCabaz(2, 1, 0, 1, producersCabaz, "C1", 1);
        List<StatisticsByCabaz> expected = new ArrayList<>();
        expected.add(statistics);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedition list statistics complete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpeditionListStatisticsComplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_ExpeditionListStatisticsComplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        ExpeditionStatistics result = mainController.getExpeditionListStatistics();

        List<String> producersCabaz = new ArrayList<>();
        producersCabaz.add("P1");
        ExpeditionStatistics expected = new ExpeditionStatistics(1, 2, 0, 0, 1, producersCabaz);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test expedition list statistics incomplete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testExpeditionListStatisticsIncomplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_ExpeditionListStatisticsIncomplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        ExpeditionStatistics result = mainController.getExpeditionListStatistics();

        List<String> producersCabaz = new ArrayList<>();
        producersCabaz.add("P1");
        producersCabaz.add("Produtor Inexistente");
        ExpeditionStatistics expected = new ExpeditionStatistics(1, 0, 2, 1, 2, producersCabaz);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test producers statistics complete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testProducersStatisticsComplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByProdutor> result = mainController.getProducerStatistics();
        Produtor producer = new Produtor("CT17", "P1", new Point2D.Double(40.6667, -7.9167));
        List<Empresa> hubs = new ArrayList<>();
        Empresa hub = new Empresa("CT9", "E4", new Point2D.Double(40.5364, -7.2683));
        hubs.add(hub);
        StatisticsByProdutor statistics = new StatisticsByProdutor(producer, 1, 0, 0, hubs);
        List<StatisticsByProdutor> expected = new ArrayList<>();
        expected.add(statistics);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test producers statistics incomplete.
     *
     * @throws Exception the exception
     */
    @Test
    public void testProducersStatisticsIncomplete() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestrictionIncomplete.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByProdutor> result = mainController.getProducerStatistics();
        Produtor producer = new Produtor("CT17", "P1", new Point2D.Double(40.6667, -7.9167));
        List<Empresa> hubs = new ArrayList<>();
        Empresa hub = new Empresa("CT9", "E4", new Point2D.Double(40.5364, -7.2683));
        hubs.add(hub);
        StatisticsByProdutor statistics = new StatisticsByProdutor(producer, 0, 1, 1, hubs);
        List<StatisticsByProdutor> expected = new ArrayList<>();
        expected.add(statistics);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test producers statistics extra products.
     *
     * @throws Exception the exception
     */
    @Test
    public void testProducersStatisticsExtraProducts() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_ProducerStatisticsExtraProducts.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByProdutor> result = mainController.getProducerStatistics();
        Produtor producer = new Produtor("CT17", "P1", new Point2D.Double(40.6667, -7.9167));
        List<Empresa> hubs = new ArrayList<>();
        Empresa hub = new Empresa("CT9", "E4", new Point2D.Double(40.5364, -7.2683));
        hubs.add(hub);
        StatisticsByProdutor statistics = new StatisticsByProdutor(producer, 1, 0, 0, hubs);
        List<StatisticsByProdutor> expected = new ArrayList<>();
        expected.add(statistics);

        System.out.println("Expected List:");
        System.out.println(expected);
        System.out.println("Actual list:");
        System.out.println(result);

        Assertions.assertEquals(expected.toString(), result.toString(), "Test passed!");
    }

    /**
     * Test hub statistics.
     *
     * @throws Exception the exception
     */
    @Test
    public void testHubStatistics() throws Exception {
        URL cabazUrl = Thread.currentThread().getContextClassLoader().getResource("Cabazes_NoRestriction.csv");
        mainController.lerCabaz(cabazUrl.getPath());
        mainController.criarStockAndCabaz();
        mainController.expedatingForDayNoRestrictions(1);

        List<StatisticsByHub> expected = new ArrayList<>();
        List<StatisticsByHub> result = mainController.getHubStatistics();

        Empresa hub1 = new Empresa("CT14", "E1", new Point2D.Double(38.5243, -8.8926));
        Empresa hub2 = new Empresa("CT11", "E2", new Point2D.Double(39.3167, -7.4167));
        Empresa hub3 = new Empresa("CT5", "E3", new Point2D.Double(39.823, -7.4931));
        Empresa hub4 = new Empresa("CT9", "E4", new Point2D.Double(40.5364, -7.2683));
        Empresa hub5 = new Empresa("CT4", "E5", new Point2D.Double(41.8, -6.75));

        StatisticsByHub statisticsHub1 = new StatisticsByHub(hub1, 0, 0);
        StatisticsByHub statisticsHub2 = new StatisticsByHub(hub2, 0, 0);
        StatisticsByHub statisticsHub3 = new StatisticsByHub(hub3, 0, 0);
        StatisticsByHub statisticsHub4 = new StatisticsByHub(hub4, 1, 1);
        StatisticsByHub statisticsHub5 = new StatisticsByHub(hub5, 0, 0);

        expected.add(statisticsHub1);
        expected.add(statisticsHub2);
        expected.add(statisticsHub3);
        expected.add(statisticsHub4);
        expected.add(statisticsHub5);

        Assertions.assertEquals(expected.toString(), result.toString());
    }

}
