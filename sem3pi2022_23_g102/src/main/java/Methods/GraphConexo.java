package Methods;

import Company.Graph.Algorithms;
import Company.Graph.Edge;
import Company.Graph.map.MapGraph;
import Company.Graph.matrix.MatrixGraph;
import Company.Model.Pessoa;
import Company.Store.MapGraphPessoaStore;

import java.util.*;
import java.util.function.BinaryOperator;

/**
 * The type Graph conexo.
 */
public class GraphConexo {

    private final Comparator<Integer> comparator = Integer::compareTo;
    private final BinaryOperator<Integer> binaryOperator = Integer::sum;
    private MapGraphPessoaStore mapGraphPessoaStore;


    /**
     * Instantiates a new Graph conexo.
     *
     * @param mapGraphPessoaStore the map graph pessoa store
     */
    public GraphConexo(MapGraphPessoaStore mapGraphPessoaStore) {
        this.mapGraphPessoaStore = mapGraphPessoaStore;
    }

    // US302


    /**
     * It checks if the graph is connected by doing a Breadth First Search from each vertex and checking if the number of
     * vertices in the graph is equal to the number of vertices in the BFS
     *
     * @return The method verificarGraphConexo() returns a boolean value.
     */
    public boolean verificarGraphConexo() {

        List<Pessoa> pessoaList = mapGraphPessoaStore.getMapGraph().vertices(); // O(V)

        for (Pessoa pessoaAux : pessoaList) {// O(n)
            List<Pessoa> listAux = Algorithms.BreadthFirstSearch(mapGraphPessoaStore.getMapGraph(), pessoaAux);//O(|E| + |V|)

            if (listAux == null) {
                System.out.println("Grafo nao conexo");
                return false;
            }
            if (listAux.size() == pessoaList.size()) {
                System.out.println("Grafo conexo");
                System.out.println(listAux.size() + " nr de vertices"); // O(1)
                return true;
            }
        }
        System.out.println("Grafo nao conexo");
        return false;
    }// O(|E| + |V|)


    /**
     * It returns the number of connections in the largest cycle in the graph.
     *
     * @return The number of connections in the largest diamond.
     */
    public int numeroLigacoesDiam() {
        MapGraph<Pessoa, Integer> mapGraph = mapGraphPessoaStore.getMapGraph();
        int max = -1, helper;

        if (mapGraph == null) {
            return max;
        }

        List<Pessoa> pessoaList = mapGraphPessoaStore.getMapGraph().vertices();// O(V)

        // O(n^6)
        for (Pessoa pessoa : pessoaList) {// O(n)
            ArrayList<LinkedList<Pessoa>> listAux = Algorithms.vertCycles(mapGraph, pessoa, comparator); // O(n^4)
            helper = biggestSize(listAux);// O(n)
            if (max < helper) {
                max = helper;
            }
        }
        return max;
    }// O(n^6)

// Diameter

    /**
     * It creates a new graph with the same vertices and edges as the original graph, but with all the edges having weight
     * 1. Then it calculates the minimum distance graph between all the vertices of the new graph, and returns the maximum
     * weight of the edges of the minimum distance graph
     *
     * @return The number of connections between two people.
     */
    public int numeroLigacoesDiamOpcaoDois() {
        MapGraph<Pessoa, Integer> mapGraph = new MapGraph<>(mapGraphPessoaStore.getMapGraph());

        ArrayList<Pessoa> pessoasList = mapGraph.vertices();// O(V)
        Collection<Edge<Pessoa, Integer>> edges = mapGraph.edges();// O(E)

        for (Edge<Pessoa, Integer> edge : edges) {// O(E)
            edge.setWeight(1);
        }

        MatrixGraph<Pessoa, Integer> minDistGraph = Algorithms.minDistGraph(mapGraph, comparator, binaryOperator);// O(n^3)
        int max = 0;

        for (Pessoa pessoa1 : pessoasList) {// O(n)
            for (Pessoa pessoa2 : pessoasList) {// O(n)
                if (pessoa1 != pessoa2) {
                    int aux = minDistGraph.edge(pessoa1, pessoa2).getWeight();
                    if (aux > max) {
                        max = aux;
                    }
                }
            }
        }
        return max;
    }// O(n^3)


    /**
     * It returns the biggest size of a LinkedList in an ArrayList
     *
     * @param listAux The list of lists that will be used to sort the list of people.
     * @return The biggest size of the list.
     */
    private int biggestSize(ArrayList<LinkedList<Pessoa>> listAux) {
        OptionalInt max = listAux.stream().mapToInt(LinkedList::size).max(); // O(n)

        return max.getAsInt() - 1;
    }
}
