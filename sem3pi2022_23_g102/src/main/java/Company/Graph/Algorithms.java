package Company.Graph;

import Company.Graph.matrix.MatrixGraph;

import java.util.*;
import java.util.function.BinaryOperator;


/**
 * The type Algorithms.
 */
public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param <V>  the type parameter
     * @param <E>  the type parameter
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert))
            return null;

        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];

        qbfs.add(vert);
        qaux.add(vert);
        int vKey = g.key(vert);
        visited[vKey] = true;

        while (!qaux.isEmpty()) {
            vert = qaux.remove();
            for (V vAdj : g.adjVertices(vert)) {
                vKey = g.key(vAdj);
                if (!visited[vKey]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[vKey] = true;
                }
            }
        }
        return qbfs;
    }//O(|E| + |V|) where |V| and |E| is the cardinality of set of vertices and edges respectively.

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {

        int vKey = g.key(vOrig);
        if (visited[vKey]) return;

        qdfs.add(vOrig);
        visited[vKey] = true;

        // O(n^2)
        for (V vAdj : g.adjVertices(vOrig)) { // O(n)
            DepthFirstSearch(g, vAdj, visited, qdfs); // O(n)
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param <V>  the type parameter
     * @param <E>  the type parameter
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
// O(n^2)
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {

        if (!g.validVertex(vert))// O(n)
            return null;

        LinkedList<V> qdfs = new LinkedList<>();
        boolean[] visited = new boolean[g.numVertices()];

        DepthFirstSearch(g, vert, visited, qdfs); // O(n^2)

        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    // O(n^2)
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                        LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        int vKey = g.key(vOrig);
        if (visited[vKey]) return;

        if (vOrig.equals(vDest)) {   // O(1)       //save clone of reverse path
            LinkedList<V> pathcopy = new LinkedList<>(path);
            pathcopy.addFirst(vDest); // O(1)
            Collections.reverse(pathcopy); // O(n)
            paths.add(new LinkedList<>(pathcopy));// O(1)
            return;
        }

        path.push(vOrig);
        visited[vKey] = true;

        //O(n^2)
        for (V vAdj : g.adjVertices(vOrig)) { //O(n)
            allPaths(g, vAdj, vDest, visited, path, paths); //O(n)
        }

        path.pop();
        visited[vKey] = false;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param <V>   the type parameter
     * @param <E>   the type parameter
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
//O(n^2)
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        LinkedList<V> path = new LinkedList<>();
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];

        if (g.validVertex(vOrig) && g.validVertex(vDest))
            allPaths(g, vOrig, vDest, visited, path, paths); //O(n^2)

        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig,
                                                    Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                                    boolean[] visited, V[] pathKeys, E[] dist) {
        int vkey = g.key(vOrig);
        dist[vkey] = zero;
        pathKeys[vkey] = vOrig;
        while (vOrig != null) {
            vkey = g.key(vOrig);
            visited[vkey] = true;
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {//O(E)
                int vkeyAdj = g.key(edge.getVDest()); // O(1)
                if (!visited[vkeyAdj]) {
                    E s = sum.apply(dist[vkey], edge.getWeight());
                    if (dist[vkeyAdj] == null || ce.compare(dist[vkeyAdj], s) > 0) {
                        dist[vkeyAdj] = s;
                        pathKeys[vkeyAdj] = vOrig;
                    }
                }
            }
            E minDist = null;  //next vertice, that has minimum dist
            vOrig = null;
            for (V vert : g.vertices()) { //O(V)
                int i = g.key(vert);
                if (!visited[i] && (dist[i] != null) && ((minDist == null) || ce.compare(dist[i], minDist) < 0)) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }//O(V^2)

    private static <V, E> void initializePathDist(int nVerts, V[] pathKeys, E[] dist) {
        for (int i = 0; i < nVerts; i++) {
            dist[i] = null;
            pathKeys[i] = null;
        }
    }


    /**
     * Shortest-path between two vertices
     *
     * @param <V>       the type parameter
     * @param <E>       the type parameter
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest,
                                        Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                        LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest))
            return null;

        shortPath.clear();
        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts]; //default value: false
        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];
        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];
        initializePathDist(numVerts, pathKeys, dist);

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        E lengthPath = dist[g.key(vDest)];

        if (lengthPath != null) {
            getPath(g, vOrig, vDest, pathKeys, shortPath);
            return lengthPath;
        }
        return null;
    }

    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param <V>   the type parameter
     * @param <E>   the type parameter
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        if (!g.validVertex(vOrig)) return false;

        paths.clear();
        dists.clear();
        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts]; //default value: false
        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];
        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];
        initializePathDist(numVerts, pathKeys, dist);

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < numVerts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (V vDst : g.vertices()) {
            int i = g.key(vDst);
            if (dist[i] != null) {
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDst, pathKeys, shortPath);
                paths.set(i, shortPath);
                dists.set(i, dist[i]);
            }
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest,
                                       V[] pathKeys, LinkedList<V> path) {


        if (vOrig.equals(vDest))
            path.push(vOrig);
        else {
            path.push(vDest);
            int vKey = g.key(vDest);
            vDest = pathKeys[vKey];
            getPath(g, vOrig, vDest, pathKeys, path);
        }
    }

    /**
     * Calculates the minimum distance graph using Floyd-Warshall
     *
     * @param <V> the type parameter
     * @param <E> the type parameter
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @param sum sum two elements of type E
     * @return the minimum distance graph
     */
// O(n^3)
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce, BinaryOperator<E> sum) {
        int numVerts = g.numVertices();
        if (numVerts == 0) return null;

        @SuppressWarnings("unchecked")
        E[][] m = (E[][]) new Object[numVerts][numVerts];

        // O(n^2)
        for (int i = 0; i < numVerts; i++)// O(n)
            for (int j = 0; j < numVerts; j++) {// O(n)
                Edge<V, E> e = g.edge(i, j);
                if (e != null) m[i][j] = e.getWeight();
            }

        // O(n^3)
        for (int k = 0; k < numVerts; k++)// O(n)
            for (int i = 0; i < numVerts; i++)// O(n)
                if (i != k && m[i][k] != null)
                    for (int j = 0; j < numVerts; j++)// O(n)
                        if (j != i && j != k && m[k][j] != null) {
                            E s = sum.apply(m[i][k], m[k][j]);
                            if ((m[i][j] == null) || ce.compare(m[i][j], s) > 0) m[i][j] = s;
                        }

        return new MatrixGraph<>(g.isDirected(), g.vertices(), m);
    }

    /**
     * This Method runs a modified version of the allPaths algorithm where now it doesn't backtrack
     * This way it makes all the cycles a simple depthFirstSearch can find in a first reading and adds them to a single list
     * after which the method only returns the cycles that contain in them the provided vertex as these can be moved in a way where the vertex is at the start
     *
     * @param <V>   the type parameter
     * @param <E>   the type parameter
     * @param g     initial graph
     * @param vOrig the desired vertex
     * @param ce    the ce
     * @return returns an arraylist listing all the cycles where the vertex is present
     */
    public static <V, E> ArrayList<LinkedList<V>> vertCycles(Graph<V, E> g, V vOrig, Comparator<E> ce) {
        boolean[] visited = new boolean[g.numVertices()];//O(n)
        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        for (V v : g.vertices()) {//O(V)
            Arrays.fill(visited, false);
            allPathNoBacktracking(g, v, v, visited, new LinkedList<>(), paths, ce);// O(n^4)
        }
        paths.removeIf(path -> !path.contains(vOrig));// O(n)
        return paths;
    }//O(n^4*V)

    /**
     * The initial allPath's algorithm has a complexity of O(V!) which is unusable for graphs as big as the one we are using
     * Therefore this method does not backtrack but is made up for the fact that it is run for every
     * vertex in the graph in order to obtain a better certainty.
     * It is a heuristic that does not give 100% certain results but is pretty close and the complexity is a nice O(V)
     *
     * @param g       initial graph
     * @param vOrig   the vertex which will loop on itself
     * @param visited an array with l the visited node for the current search
     * @param path    a list with the current path being searched
     * @param paths   a list with all the cycles that have been found
     */
    private static <V, E> void allPathNoBacktracking(Graph<V, E> g, V vOrig, V vDest, boolean[] visited,
                                                     LinkedList<V> path, ArrayList<LinkedList<V>> paths, Comparator<E> ce) {
        path.push(vOrig);
        visited[g.key(vOrig)] = true;
        ArrayList<V> adjVertices = (ArrayList<V>) g.adjVertices(vOrig);
        adjVertices.sort(new Comparator<V>() {
            @Override
            public int compare(V v, V t1) {
                return ce.compare(g.edge(vOrig, v).getWeight(), g.edge(vOrig, t1).getWeight());
            }
        });

        // O(n^4)
        for (V vAdj : adjVertices) { // O(n)
            if (vAdj == vDest) {
                path.push(vDest);
                LinkedList<V> reversed = new LinkedList<V>();
                for (V v : path) { // O(m)
                    reversed.push(v);
                }
                paths.add(reversed);
                path.pop(); // O(k)
            } else if (!visited[g.key(vAdj)])
                allPathNoBacktracking(g, vAdj, vDest, visited, path, paths, ce); // O(n)
        }
        path.pop();// O(n)
    }// O(n^4)

    /**
     * Gets a list of lists with all the possible permutations of a given list of elements
     *
     * @param <E>      the type parameter
     * @param original the list of elements
     * @return a list of list with all the possible permutations
     */
    public static <E> List<List<E>> generatePerm(List<E> original) {

        if (original.isEmpty()) {
            List<List<E>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        E firstElement = original.remove(0);
        List<List<E>> returnValue = new ArrayList<>();
        List<List<E>> permutations = generatePerm(original);

        for (List<E> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<E> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }

        return returnValue;
    }

}