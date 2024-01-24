package Company.Store;

import Company.Graph.Algorithms;
import Company.Graph.map.MapGraph;
import Company.Model.Pessoa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The type Map graph pessoa store.
 */
public class MapGraphPessoaStore {


    private MapGraph<Pessoa, Integer> mapGraphPessoa;

    /**
     * Instantiates a new Map graph pessoa store.
     */
// The constructor of the class.
    public MapGraphPessoaStore() {
        this.mapGraphPessoa = new MapGraph<>(false);
    }


    /**
     * It returns the mapGraphPessoa variable
     *
     * @return The mapGraphPessoa
     */
    public MapGraph<Pessoa, Integer> getMapGraph() {
        return this.mapGraphPessoa;
    }

    /**
     * It sets the graph that will be used by the algorithm
     *
     * @param graph The graph to be used.
     */
    public void setMapGraph(MapGraph<Pessoa, Integer> graph) {
        this.mapGraphPessoa = graph;
    }


    /**
     * It returns the vertex of the graph that corresponds to the key passed as a parameter
     *
     * @param key The key of the vertex you want to find.
     * @return The key of the vertex.
     */
    public Pessoa mapPessoabyKey(int key) {

        ArrayList<Pessoa> vertices = this.mapGraphPessoa.vertices();

        if (key < 0 || key >= vertices.size())
            return null;

        return vertices.get(key);
    }

    /**
     * It returns a map with the number of routes for each person
     *
     * @return A map with the number of routes of each person.
     */
    public Map<Pessoa, Integer> nroutesPessoa() {

        Map<Pessoa, Integer> m = new HashMap<>();

        for (Pessoa pessoa : this.mapGraphPessoa.vertices()) {
            int grau = this.mapGraphPessoa.outDegree(pessoa) + this.mapGraphPessoa.inDegree(pessoa);
            m.put(pessoa, grau);
        }

        return m;
    }

    /**
     * Given a graph of people, return true if two people are connected, false otherwise.
     *
     * @param pessoa1 The first person to be connected.
     * @param pessoa2 The person you want to know if you can reach.
     * @return It is being returned if the person 2 is connected to person 1.
     */
    public Boolean connectPessoas(Pessoa pessoa1, Pessoa pessoa2) {

        LinkedList<Pessoa> listPessoas = Algorithms.DepthFirstSearch(this.mapGraphPessoa, pessoa1);

        assert listPessoas != null;
        return listPessoas.contains(pessoa2);
    }

    /**
     * The function toString() is a method of the Object class that returns a string representation of the object
     *
     * @return The mapGraphPessoa.toString() method is being returned.
     */
    @Override
    public String toString() {
        return this.mapGraphPessoa.toString();
    }

}
