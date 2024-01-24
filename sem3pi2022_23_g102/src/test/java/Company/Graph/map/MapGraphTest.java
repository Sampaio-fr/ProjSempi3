package Company.Graph.map;

import Company.Graph.Edge;
import Company.Graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Map graph test.
 */
public class MapGraphTest {

    /**
     * The L 1.
     */
    final ArrayList<String> l1 = new ArrayList<>(Arrays.asList("V1", "V1", "V2", "V3", "V3", "V4", "V5", "V5"));
    /**
     * The L 2.
     */
    final ArrayList<String> l2 = new ArrayList<>(Arrays.asList("V2", "V3", "V4", "V4", "V5", "V1", "V4", "V5"));
    /**
     * The L 3.
     */
    final ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

    /**
     * The L 4.
     */
    final ArrayList<String> l4 = new ArrayList<>(Arrays.asList("V1", "V2", "V3", "V4", "V5"));
    /**
     * The Instance.
     */
    MapGraph<String, Integer> instance = null;

    /**
     * Initialize graph.
     */
    @BeforeEach
    public void initializeGraph() {
        instance = new MapGraph<>(false);
    }

    /**
     * Test of copy constructor of class Graph.
     */
    @Test
    public void testCopyConstructor() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Graph<String, Integer> g = new MapGraph<>(instance);
        Assertions.assertEquals(instance.getClass(), g.getClass());
        Assertions.assertEquals(instance, g);
    }

    /**
     * Test of isDirected method, of class Graph.
     */
    @Test
    public void testIsDirected() {
        Assertions.assertFalse(instance.isDirected());
        instance = new MapGraph<>(true);
        Assertions.assertTrue(instance.isDirected());
    }

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        Assertions.assertEquals(0, instance.numVertices());
        instance.addVertex("V1");
        Assertions.assertEquals(1, instance.numVertices());
        instance.addVertex("V2");
        Assertions.assertEquals(2, instance.numVertices());
        instance.removeVertex("V1");
        Assertions.assertEquals(1, instance.numVertices());
        instance.removeVertex("V2");
        Assertions.assertEquals(0, instance.numVertices());
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        Assertions.assertEquals(0, instance.vertices().size());

        instance.addVertex("V1");
        instance.addVertex("V2");
        Assertions.assertEquals(2, instance.vertices().size());

        instance.removeVertex("V1");
        Assertions.assertEquals(1, instance.vertices().size());

        instance.removeVertex("V2");
        Assertions.assertEquals(0, instance.vertices().size());
    }

    /**
     * Test of validVertex method, of class Graph.
     */
    @Test
    public void testValidVertex() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        for (String v : l1)
            Assertions.assertTrue(instance.validVertex(v));

        Assertions.assertFalse(instance.validVertex("V6"));
    }

    /**
     * Test of key method, of class Graph.
     */
    @Test
    public void testKey() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        for (int i = 0; i < l4.size(); i++)
            Assertions.assertEquals(i, instance.key(l4.get(i)));

        Assertions.assertEquals(-1, instance.key("V6"));
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        Assertions.assertEquals(0, instance.numEdges());

        instance.addEdge("V1", "V2", 1);
        Assertions.assertEquals(2, instance.numEdges());

        instance.addEdge("V1", "V3", 2);
        Assertions.assertEquals(4, instance.numEdges());

        instance.removeEdge("V1", "V2");
        Assertions.assertEquals(2, instance.numEdges());

        instance.removeEdge("V1", "V3");
        Assertions.assertEquals(0, instance.numEdges());
    }

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        Assertions.assertEquals(0, instance.edges().size());

        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Collection<Edge<String, Integer>> ced = instance.edges();
        Assertions.assertEquals(15, ced.size());
        for (int i = 0; i < l1.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(l1.get(finalI)) && e.getVDest().equals(l2.get(finalI))
                    && e.getWeight().equals(l3.get(finalI)));
        }
        Assertions.assertEquals(7, ced.size());

        instance.removeEdge("V1", "V2");
        ced = instance.edges();
        Assertions.assertEquals(13, ced.size());
        for (int i = 1; i < l1.size(); i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(l1.get(finalI)) && e.getVDest().equals(l2.get(finalI))
                    && e.getWeight().equals(l3.get(finalI)));
        }
        Assertions.assertEquals(6, ced.size());

        instance.removeEdge("V5", "V5");
        ced = instance.edges();
        Assertions.assertEquals(12, ced.size());
        for (int i = 1; i < l1.size() - 1; i++) {
            int finalI = i;
            ced.removeIf(e -> e.getVOrig().equals(l1.get(finalI)) && e.getVDest().equals(l2.get(finalI))
                    && e.getWeight().equals(l3.get(finalI)));
        }
        Assertions.assertEquals(6, ced.size());

        instance.removeEdge("V1", "V3");
        instance.removeEdge("V2", "V4");
        instance.removeEdge("V3", "V4");
        instance.removeEdge("V3", "V5");
        instance.removeEdge("V4", "V1");
        instance.removeEdge("V5", "V4");

        Assertions.assertEquals(0, instance.edges().size());
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        for (int i = 0; i < l1.size(); i++)
            assertEquals(l3.get(i), instance.edge(l1.get(i), l2.get(i)).getWeight());

        Assertions.assertNull(instance.edge("V1", "V5"));
        instance.removeEdge("V4", "V1");
        Assertions.assertNull(instance.edge("V4", "V1"));
    }

    /**
     * Test of getEdge by key method, of class Graph.
     */
    @Test
    public void testGetEdgeByKey() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        for (int i = 0; i < l1.size(); i++)
            assertEquals(l3.get(i), instance.edge(instance.key(l1.get(i)), instance.key(l2.get(i))).getWeight());

        Assertions.assertNull(instance.edge(instance.key("V1"), instance.key("V5")));
        instance.removeEdge("V4", "V1");
        Assertions.assertNull(instance.edge(instance.key("V4"), instance.key("V1")));
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(-1, instance.outDegree("V6"));
        Assertions.assertEquals(3, instance.outDegree("V1"));
        Assertions.assertEquals(2, instance.outDegree("V2"));
        Assertions.assertEquals(3, instance.outDegree("V5"));
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(-1, instance.inDegree("V6"));
        Assertions.assertEquals(3, instance.inDegree("V1"));
        Assertions.assertEquals(4, instance.inDegree("V4"));
        Assertions.assertEquals(3, instance.inDegree("V5"));
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Collection<Edge<String, Integer>> coe = instance.outgoingEdges("V3");
        Assertions.assertEquals(3, coe.size());
        coe.removeIf(e -> e.getWeight() == 2 || e.getWeight() == 4 || e.getWeight() == 5);
        Assertions.assertEquals(0, coe.size());

        coe = instance.outgoingEdges("V5");
        Assertions.assertEquals(3, coe.size());
        coe.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 7 || e.getWeight() == 8);
        Assertions.assertEquals(0, coe.size());

        instance.removeEdge("V5", "V5");

        coe = instance.outgoingEdges("V5");
        Assertions.assertEquals(2, coe.size());
        coe.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 7);
        Assertions.assertEquals(0, coe.size());

        instance.removeEdge("V5", "V4");
        instance.removeEdge("V5", "V3");

        coe = instance.outgoingEdges("V5");
        Assertions.assertEquals(0, coe.size());
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Collection<Edge<String, Integer>> cie = instance.incomingEdges("V4");
        Assertions.assertEquals(4, cie.size());
        cie.removeIf(e -> e.getWeight() == 3 || e.getWeight() == 4 || e.getWeight() == 6 || e.getWeight() == 7);
        Assertions.assertEquals(0, cie.size());

        cie = instance.incomingEdges("V5");
        Assertions.assertEquals(3, cie.size());
        cie.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 7 || e.getWeight() == 8);
        Assertions.assertEquals(0, cie.size());

        instance.removeEdge("V5", "V5");

        cie = instance.incomingEdges("V5");
        Assertions.assertEquals(2, cie.size());
        cie.removeIf(e -> e.getWeight() == 5 || e.getWeight() == 7);
        Assertions.assertEquals(0, cie.size());

        instance.removeEdge("V3", "V5");
        instance.removeEdge("V4", "V5");

        cie = instance.incomingEdges("V5");
        Assertions.assertEquals(0, cie.size());
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(5, instance.numVertices());
        Assertions.assertEquals(15, instance.numEdges());

        instance.removeVertex("V1");
        Assertions.assertEquals(4, instance.numVertices());
        Assertions.assertEquals(9, instance.numEdges());

        instance.removeVertex("V2");
        Assertions.assertEquals(3, instance.numVertices());
        Assertions.assertEquals(7, instance.numEdges());

        instance.removeVertex("V3");
        Assertions.assertEquals(2, instance.numVertices());
        Assertions.assertEquals(3, instance.numEdges());

        instance.removeVertex("V4");
        Assertions.assertEquals(1, instance.numVertices());
        Assertions.assertEquals(1, instance.numEdges());

        instance.removeVertex("V5");
        Assertions.assertEquals(0, instance.numVertices());
        Assertions.assertEquals(0, instance.numEdges());
    }

    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {
        Assertions.assertEquals(0, instance.numEdges());

        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(5, instance.numVertices());
        Assertions.assertEquals(15, instance.numEdges());

        for (Edge<String, Integer> edge : instance.edges()) {
            instance.removeEdge(edge.getVOrig(), edge.getVDest());
        }
        Assertions.assertEquals(0, instance.numEdges());
    }

    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(5, instance.numVertices());
        Assertions.assertEquals(15, instance.numEdges());

        Graph<String, Integer> instClone = instance.clone();

        for (int i = 0; i < l1.size(); i++) {
            Edge<String, Integer> ec = instClone.edge(l1.get(i), l2.get(i));
            assertEquals(l1.get(i), ec.getVOrig());
            assertEquals(l2.get(i), ec.getVDest());
            assertEquals(l3.get(i), ec.getWeight());
        }

        for (String v : l1)
            instClone.removeVertex(v);

        Assertions.assertEquals(5, instance.numVertices());
        Assertions.assertEquals(15, instance.numEdges());
        Assertions.assertEquals(0, instClone.numVertices());
        Assertions.assertEquals(0, instClone.numEdges());
    }

    /**
     * Test equals.
     */
    @Test
    public void testEquals() {
        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        MapGraph<String, Integer> otherInst = new MapGraph<>(false);
        for (int i = 0; i < l1.size(); i++)
            otherInst.addEdge(l1.get(i), l2.get(i), l3.get(i));

        Assertions.assertEquals(instance, otherInst);

        otherInst.removeVertex("V1");

        Assertions.assertNotEquals(instance, otherInst);

        instance.removeVertex("V1");

        Assertions.assertEquals(instance, otherInst);

        otherInst.removeEdge("V3", "V5");

        Assertions.assertNotEquals(instance, otherInst);

        instance.removeEdge("V3", "V5");

        Assertions.assertEquals(instance, otherInst);
    }

    /**
     * Test un directed graph.
     */
    @Test
    public void testUnDirectedGraph() {
        instance = new MapGraph<>(false);

        for (int i = 0; i < l1.size(); i++)
            instance.addEdge(l1.get(i), l2.get(i), l3.get(i));

        for (int i = 0; i < l1.size(); i++) {
            Edge<String, Integer> edge = instance.edge(l1.get(i), l2.get(i));
            Assertions.assertEquals(l1.get(i), edge.getVOrig());
            Assertions.assertEquals(l2.get(i), edge.getVDest());
            Assertions.assertEquals(l3.get(i), edge.getWeight());
            Edge<String, Integer> ecu = instance.edge(l2.get(i), l1.get(i));
            Assertions.assertEquals(l2.get(i), ecu.getVOrig());
            Assertions.assertEquals(l1.get(i), ecu.getVDest());
            Assertions.assertEquals(l3.get(i), ecu.getWeight());
        }

        instance.removeEdge(l1.get(0), l2.get(0));

        for (int i = 1; i < l1.size(); i++) {
            Edge<String, Integer> edge = instance.edge(l1.get(i), l2.get(i));
            Assertions.assertEquals(l1.get(i), edge.getVOrig());
            Assertions.assertEquals(l2.get(i), edge.getVDest());
            Assertions.assertEquals(l3.get(i), edge.getWeight());
            Edge<String, Integer> ecu = instance.edge(l2.get(i), l1.get(i));
            Assertions.assertEquals(l2.get(i), ecu.getVOrig());
            Assertions.assertEquals(l1.get(i), ecu.getVDest());
            Assertions.assertEquals(l3.get(i), ecu.getWeight());
        }
    }
}