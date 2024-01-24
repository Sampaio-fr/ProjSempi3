package Company.Graph.map;

import Company.Graph.Edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The type Map vertex.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public class MapVertex<V, E> {

    final private V element;                            // Vertex information
    final private Map<V, Edge<V, E>> outVerts;    // Adjacent vertices

    /**
     * Instantiates a new Map vertex.
     *
     * @param vert the vert
     */
    public MapVertex(V vert) {
        if (vert == null) throw new RuntimeException("Vertice information cannot be null!");
        element = vert;
        outVerts = new LinkedHashMap<>();
    }

    /**
     * Returns the element stored in this node.
     *
     * @return The element of the node.
     */
    public V getElement() {
        return element;
    }

    /**
     * This function adds an edge to the adjacency list of the vertex
     *
     * @param vAdj The vertex that is adjacent to the current vertex.
     * @param edge The edge that connects the current vertex to the vertex being added.
     */
    public void addAdjVert(V vAdj, Edge<V, E> edge) {
        outVerts.put(vAdj, edge);
    }

    /**
     * Given a vertex, return the edge that connects this vertex to the vertex passed in as a parameter.
     *
     * @param vAdj The vertex that is adjacent to the current vertex.
     * @return The edge that connects the vertex to the vertex passed in as a parameter.
     */
    public void remAdjVert(V vAdj) {
        outVerts.remove(vAdj);
    }

    /**
     * Given a vertex, return the edge that connects this vertex to the vertex passed in as a parameter.
     *
     * @param vAdj The vertex that is adjacent to the current vertex.
     * @return The edge that connects the vertex to the vertex passed in as a parameter.
     */
    public Edge<V, E> getEdge(V vAdj) {
        return outVerts.get(vAdj);
    }

    /**
     * Returns the number of adjacent vertices.
     *
     * @return The number of adjacent vertices.
     */
    public int numAdjVerts() {
        return outVerts.size();
    }

    /**
     * Return a collection of all the vertices adjacent to this vertex.
     *
     * @return A collection of all the vertices that are adjacent to this vertex.
     */
    public Collection<V> getAllAdjVerts() {
        return new ArrayList<>(outVerts.keySet());
    }

    /**
     * Return a collection of all the edges that are outgoing from this vertex.
     *
     * @return A collection of all the outgoing edges from this vertex.
     */
    public Collection<Edge<V, E>> getAllOutEdges() {
        return new ArrayList<>(outVerts.values());
    }

    /**
     * The function returns a string representation of the vertex, which includes the element and the edges that are
     * connected to it
     *
     * @return The element of the vertex, followed by a list of all the edges that are connected to it.
     */
    @Override
    public String toString() {
        String st = element + ": \n";
        if (!outVerts.isEmpty())
            for (V vert : outVerts.keySet())
                st += outVerts.get(vert);

        return st;
    }
}
