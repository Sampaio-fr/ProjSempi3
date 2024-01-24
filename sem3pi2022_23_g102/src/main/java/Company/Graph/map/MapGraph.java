package Company.Graph.map;

import Company.Graph.CommonGraph;
import Company.Graph.Edge;
import Company.Graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The type Map graph.
 *
 * @param <V> the type parameter
 * @param <E> the type parameter
 */
public class MapGraph<V, E> extends CommonGraph<V, E> {


    final private Map<V, MapVertex<V, E>> mapVertices;  // all the Vertices of the graph

    /**
     * Instantiates a new Map graph.
     *
     * @param directed the directed
     */
// Constructs an empty graph (either undirected or directed)
    public MapGraph(boolean directed) {
        super(directed);
        mapVertices = new LinkedHashMap<>();
    }

    /**
     * Instantiates a new Map graph.
     *
     * @param g the g
     */
// A copy constructor.
    public MapGraph(Graph<V, E> g) {
        this(g.isDirected());
        copy(g, this);
    }

    /**
     * If the vertex is in the map, then it is valid.
     *
     * @param vert The vertex to check.
     * @return A boolean value.
     */
    @Override
    public boolean validVertex(V vert) {
        return (mapVertices.get(vert) != null);
    }

    /**
     * "Given a vertex, return a collection of all the vertices that are adjacent to it."
     * <p>
     * The function is called adjVertices, and it takes a vertex as a parameter.
     * <p>
     * The first thing the function does is get the MapVertex object that corresponds to the vertex that was passed in.
     * <p>
     * If the MapVertex object is null, then the vertex doesn't exist in the graph, so the function returns null.
     * <p>
     * If the MapVertex object is not null, then the function returns the collection of all the vertices that are adjacent
     * to the vertex that was passed in.
     * <p>
     * The function returns a collection of vertices, not a collection of edges.
     * <p>
     * The function returns a collection of vertices because the graph is undirected.
     * <p>
     * If the graph were directed, then the function would return a collection of edges.
     * <p>
     * The function returns a collection of vertices because the graph
     *
     * @param vert The vertex to get the adjacent vertices of.
     * @return A collection of all the adjacent vertices to the given vertex.
     */
    @Override
    public Collection<V> adjVertices(V vert) {
        MapVertex<V, E> mv = mapVertices.get(vert);

        if (mv == null)
            return null;
        return mv.getAllAdjVerts();
    }

    /**
     * > For each vertex in the graph, add all of its outgoing edges to a list
     *
     * @return A collection of all the edges in the graph.
     */
    @Override
    public Collection<Edge<V, E>> edges() {

        ArrayList<Edge<V, E>> le = new ArrayList<>(numEdges);

        for (MapVertex<V, E> mv : mapVertices.values())//O(m)
            le.addAll(mv.getAllOutEdges()/*  O(k)  */);//O(n)

        return le;
    }//O(n^3)

    /**
     * > If the vertices are valid, get the MapVertex object for the origin vertex and return the edge from the origin to
     * the destination
     *
     * @param vOrig The origin vertex of the edge.
     * @param vDest The destination vertex of the edge.
     * @return The edge between the two vertices.
     */
    @Override
    public Edge<V, E> edge(V vOrig, V vDest) {

        if (!validVertex(vOrig) || !validVertex(vDest))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vOrig);

        return mv.getEdge(vDest);
    }

    /**
     * Return the edge between the two vertices, or null if there is no such edge.
     *
     * @param vOrigKey The key of the origin vertex.
     * @param vDestKey The key of the destination vertex.
     * @return The edge between the two vertices.
     */
    @Override
    public Edge<V, E> edge(int vOrigKey, int vDestKey) {
        V vOrig = vertex(vOrigKey);
        V vDest = vertex(vDestKey);

        return edge(vOrig, vDest);
    }

    /**
     * "If the vertex is valid, return the number of adjacent vertices."
     * <p>
     * The first line of the function is a check to see if the vertex is valid. If it is not, the function returns -1
     *
     * @param vert The vertex whose out-degree is to be returned.
     * @return The number of edges that are connected to the vertex.
     */
    @Override
    public int outDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.numAdjVerts();
    }

    /**
     * For each vertex in the graph, if there is an edge from that vertex to the vertex passed in as a parameter, increment
     * the degree
     *
     * @param vert The vertex whose in-degree is to be returned.
     * @return The number of edges that are pointing to the vertex.
     */
    @Override
    public int inDegree(V vert) {

        if (!validVertex(vert))
            return -1;

        int degree = 0;
        for (V otherVert : mapVertices.keySet())
            if (edge(otherVert, vert) != null)
                degree++;

        return degree;
    }

    /**
     * This function returns a collection of all the outgoing edges of a vertex
     *
     * @param vert The vertex whose outgoing edges are to be returned.
     * @return A collection of all the outgoing edges of the vertex.
     */
    @Override
    public Collection<Edge<V, E>> outgoingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        MapVertex<V, E> mv = mapVertices.get(vert);

        return mv.getAllOutEdges();
    }

    /**
     * For each vertex in the graph, if there is an edge from that vertex to the vertex passed in as a parameter, add that
     * edge to the list of edges to return.
     *
     * @param vert The vertex whose incoming edges are to be returned.
     * @return A collection of all the edges that are incoming to the vertex.
     */
    @Override
    public Collection<Edge<V, E>> incomingEdges(V vert) {

        if (!validVertex(vert))
            return null;

        ArrayList<Edge<V, E>> edges = new ArrayList<>();

        for (V other : mapVertices.keySet()) {
            Edge<V, E> edge = edge(other, vert);
            if (edge != null)
                edges.add(edge);
        }
        return edges;
    }

    /**
     * If the vertex is not null and is not already in the graph, add it to the graph
     *
     * @param vert The vertex to be added to the graph.
     * @return A boolean value.
     */
    @Override
    public boolean addVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (validVertex(vert))
            return false;

        MapVertex<V, E> mv = new MapVertex<>(vert);
        vertices.add(vert);
        mapVertices.put(vert, mv);
        numVerts++;

        return true;
    }

    /**
     * If the graph is not directed, then we add an edge in the opposite direction
     *
     * @param vOrig  The origin vertex
     * @param vDest  The destination vertex.
     * @param weight The weight of the edge.
     * @return The method returns a boolean value.
     */
    @Override
    public boolean addEdge(V vOrig, V vDest, E weight) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (edge(vOrig, vDest) != null)
            return false;

        if (!validVertex(vOrig))
            addVertex(vOrig);

        if (!validVertex(vDest))
            addVertex(vDest);

        MapVertex<V, E> mvo = mapVertices.get(vOrig);
        MapVertex<V, E> mvd = mapVertices.get(vDest);

        Edge<V, E> newEdge = new Edge<>(mvo.getElement(), mvd.getElement(), weight);
        mvo.addAdjVert(mvd.getElement(), newEdge);
        numEdges++;

        //if graph is not direct insert other edge in the opposite direction
        if (!isDirected)
            // if vDest different vOrig
            if (edge(vDest, vOrig) == null) {
                Edge<V, E> otherEdge = new Edge<>(mvd.getElement(), mvo.getElement(), weight);
                mvd.addAdjVert(mvo.getElement(), otherEdge);
                numEdges++;
            }

        return true;
    }

    /**
     * > Remove all edges that point to the vertex, then remove the vertex
     *
     * @param vert The vertex to be removed
     * @return The number of vertices in the graph.
     */
    @Override
    public boolean removeVertex(V vert) {

        if (vert == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vert))
            return false;

        //remove all edges that point to vert
        for (Edge<V, E> edge : incomingEdges(vert)) {
            removeEdge(edge.getVOrig(), vert);
        }

        MapVertex<V, E> mv = mapVertices.get(vert);

        //The edges that live from vert are removed with the vertex
        numEdges -= mv.numAdjVerts();
        mapVertices.remove(vert);
        vertices.remove(vert);

        numVerts--;

        return true;
    }

    /**
     * If the graph is directed, remove the edge from the origin vertex to the destination vertex. If the graph is
     * undirected, remove the edge from the origin vertex to the destination vertex and the edge from the destination
     * vertex to the origin vertex
     *
     * @param vOrig The origin vertex
     * @param vDest The destination vertex.
     * @return The edge between the two vertices.
     */
    @Override
    public boolean removeEdge(V vOrig, V vDest) {

        if (vOrig == null || vDest == null) throw new RuntimeException("Vertices cannot be null!");
        if (!validVertex(vOrig) || !validVertex(vDest))
            return false;

        Edge<V, E> edge = edge(vOrig, vDest);

        if (edge == null)
            return false;

        MapVertex<V, E> mvo = mapVertices.get(vOrig);

        mvo.remAdjVert(vDest);
        numEdges--;

        //if graph is not directed
        if (!isDirected) {
            edge = edge(vDest, vOrig);
            if (edge != null) {
                MapVertex<V, E> mvd = mapVertices.get(vDest);
                mvd.remAdjVert(vOrig);
                numEdges--;
            }
        }
        return true;
    }

    //Returns a clone of the graph

    /**
     * It creates a new graph and copies the vertices and edges from the current graph to the new graph.
     *
     * @return A new MapGraph object that is a copy of the original MapGraph object.
     */
    @Override
    public MapGraph<V, E> clone() {

        MapGraph<V, E> g = new MapGraph<>(this.isDirected);

        copy(this, g);

        return g;
    }

    //string representation

    /**
     * The function returns a string representation of the graph
     *
     * @return A string representation of the graph.
     */
    @Override
    public String toString() {
        String s;
        if (numVerts == 0) {
            s = "\nGraph not defined!!";
        } else {
            s = "Graph: " + numVerts + " vertices, " + numEdges + " edges\n";
            for (MapVertex<V, E> mv : mapVertices.values())
                s += mv + "\n";
        }
        return s;
    }
}