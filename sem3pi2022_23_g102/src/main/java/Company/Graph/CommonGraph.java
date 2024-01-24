package Company.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;


/**
 * The type Common graph.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public abstract class CommonGraph<V, E> implements Graph<V, E> {
    /**
     * The Is directed.
     */
    protected final boolean isDirected;
    /**
     * The Num verts.
     */
    protected int numVerts;
    /**
     * The Num edges.
     */
    protected int numEdges;
    /**
     * The Vertices.
     */
    protected ArrayList<V> vertices;       // Used to maintain a numeric key to each vertex

    /**
     * Instantiates a new Common graph.
     *
     * @param directed the directed
     */
    public CommonGraph(boolean directed) {
        numVerts = 0;
        numEdges = 0;
        isDirected = directed;
        vertices = new ArrayList<>();
    }

    /**
     * Returns true if the graph is directed, false otherwise.
     *
     * @return The boolean value of isDirected.
     */

    @Override
    public boolean isDirected() {
        return isDirected;
    }

    /**
     * `numVertices()` returns the number of vertices in the graph
     *
     * @return The number of vertices in the graph.
     */
    @Override
    public int numVertices() {
        return numVerts;
    }

    /**
     * It returns a list of all the vertices in the graph.
     *
     * @return A new ArrayList of vertices.
     */
    @Override
    public ArrayList<V> vertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * If the vertices set contains the vertex, return true, otherwise return false.
     *
     * @param vert The vertex to check
     * @return A boolean value.
     */
    @Override
    public boolean validVertex(V vert) {
        return vertices.contains(vert);
    }

    /**
     * Return the index of the vertex in the list of vertices.
     *
     * @param vert The vertex to get the key for.
     * @return The index of the vertex in the vertices list.
     */
    @Override
    public int key(V vert) {
        return vertices.indexOf(vert);
    }

    /**
     * If the key is valid, return the vertex at that key.
     *
     * @param key The key of the vertex to be returned.
     * @return The vertex at the given key.
     */
    @Override
    public V vertex(int key) {
        if ((key < 0) || (key >= numVerts)) return null;
        return vertices.get(key);
    }

    /**
     * Return the first vertex that satisfies the predicate.
     *
     * @param p A predicate that takes a vertex and returns true if it matches the predicate.
     * @return A vertex that satisfies the predicate.
     */
    @Override
    public V vertex(Predicate<V> p) {
        for (V v : vertices) {
            if (p.test(v)) return v;
        }
        return null;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    /**
     * Copy graph from to graph to
     *
     * @param from graph from which to copy
     * @param to   graph for which to copy
     */
    protected void copy(Graph<V, E> from, Graph<V, E> to) {
        //insert all vertices
        for (V v : from.vertices()) { //O(V)
            to.addVertex(v); //O(1)
        }

        //insert all edges
        for (Edge<V, E> e : from.edges()) {//O(E)
            to.addEdge(e.getVOrig(), e.getVDest(), e.getWeight()); //O(1)
        }
    }//O(V*E)

    /* equals implementation compares graphs, independently of their representation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj)
            return true;

        if (!(otherObj instanceof Graph<?, ?>))
            return false;

        @SuppressWarnings("unchecked") Graph<V, E> otherGraph = (Graph<V, E>) otherObj;

        if (numVerts != otherGraph.numVertices() || numEdges != otherGraph.numEdges() || isDirected() != otherGraph.isDirected())
            return false;

        // graph must have same vertices
        Collection<V> tvc = this.vertices(); //O(V)
        tvc.removeAll(otherGraph.vertices());//O(V)
        if (tvc.size() > 0) return false;

        // graph must have same edges
        Collection<Edge<V, E>> tec = this.edges(); //O(E)
        tec.removeAll(otherGraph.edges()); //O(E)
        return (tec.size() == 0);
    }

    /**
     * Returns a copy of this graph.
     *
     * @return A new graph with the same vertices and edges as this graph.
     */
    public abstract Graph<V, E> clone();

    @Override
    public int hashCode() {
        return Objects.hash(numVerts, numEdges, isDirected, vertices);
    }
}